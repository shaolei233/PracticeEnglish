package com.wx.speaking.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.wx.speaking.mapper.SentenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SentenceService {

    @Autowired
    SentenceMapper sentenceMapper;
    @Autowired
    AsyncService asyncService;

    @Async
    public void addResultToDB(String result, String user_id, Integer id){
        JSONObject res = JSONObject.parseObject(result);
        Double total_score= res.getDouble("total_score");
        Double accuracy_score = res.getDouble("accuracy_score");
        Double fluency_score = res.getDouble("fluency_score");
        Double integrity_score = res.getDouble("integrity_score");
        String wordScore = res.getString("wordScore");

        asyncService.updateSentence(id, total_score, accuracy_score, fluency_score, integrity_score, wordScore);
        asyncService.updateSentenceInfo(user_id, id,total_score, accuracy_score, fluency_score, integrity_score);

    }

    public String resolveJson(String json){

        JSONObject resultJson = new JSONObject();//返回的结果JSON
        JSONObject dataOfJson; //评测数据
        JSONObject readChapter; //章节评测部分
        JSONObject wordJson;//单词评测部分


        double total_score;//总分
        double accuracy_score;//准确度得分
        double fluency_score;//流畅度得分
        double integrity_score;//完整度得分

        DecimalFormat df = new DecimalFormat("#.00"); //定义小数点后两位的格式

        List<Double> wordScore = new ArrayList<>();
        List<Integer> dp_message = new ArrayList<>();

        JSONArray wordScoreString;
        JSONArray dpString;

        //异常状态码(28673:无语音输入或音量太小; 28676:检测到语音为乱说类型;
        // 28680:音频数据信噪比太低,音噪比在1.7以下; 28709:音频数据信噪比太低，音噪比在0.7以下; 28690:音频数据出现截幅)
        int except_info;
        int word_count;
        String is_rejected;//"true"为乱读，反之正常


        //获取json最外层对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        dataOfJson = jsonObject.getJSONObject("data");//获取数据对象

        readChapter = (JSONObject) JSONPath.eval(dataOfJson, "$.read_sentence.rec_paper.read_chapter");
        word_count = readChapter.getInteger("word_count");
        except_info = readChapter.getInteger("except_info");
        is_rejected = readChapter.getString("is_rejected");


        if(except_info !=0){
            resultJson.put("info", except_info);
            return resultJson.toJSONString();
        }else{
            resultJson.put("info", except_info);
        }


        if(is_rejected == "true"){
            resultJson.put("msg", "rejected");
            System.out.println("检测为乱读");
            return resultJson.toJSONString();
        }else{
            resultJson.put("msg", "success");
        }


        total_score = Double.parseDouble(df.format(readChapter.getDouble("total_score")));
        accuracy_score = Double.parseDouble(df.format(readChapter.getDouble("accuracy_score")));
        fluency_score = Double.parseDouble(df.format(readChapter.getDouble("fluency_score")));
        integrity_score = Double.parseDouble(df.format(readChapter.getDouble("integrity_score")));

        wordJson = (JSONObject) JSONPath.eval(readChapter, "$.sentence");

//        wordScore = (List<Double>) JSONPath.eval(wordJson,"$.word[*].total_score");//获取每个单词得分
        dpString = (JSONArray) JSONPath.eval(wordJson, "$.word[*].dp_message");
        wordScoreString = (JSONArray) JSONPath.eval(wordJson,"$.word[*].total_score");
        for(int i=0; i<word_count; i++){
            wordScore.add(Double.parseDouble(df.format(wordScoreString.getDouble(i))));
            dp_message.add(Integer.parseInt(String.valueOf(dpString.getInteger(i))));
        }

        resultJson.put("total_score", total_score);
        resultJson.put("accuracy_score", accuracy_score);
        resultJson.put("fluency_score", fluency_score);
        resultJson.put("integrity_score", integrity_score);
        resultJson.put("wordScore", wordScore);
        resultJson.put("dp", dp_message);

        String result = resultJson.toJSONString();
        System.out.println(result);
        return resultJson.toJSONString();
    }
}
