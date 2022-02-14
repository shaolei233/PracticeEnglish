package com.wx.speaking.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.wx.speaking.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class WordService {

    @Autowired
    WordMapper wordMapper;
    @Autowired
    AsyncService asyncService;

    @Async
    public void addResultToDB(String result, String user_id, Integer id){
        JSONObject res = JSONObject.parseObject(result);
        double score = res.getDouble("score");
        asyncService.updateWord(id, score);
        asyncService.updateWordInfo(user_id, id, score);
    }

    public String resolveJson(String json){
        JSONObject resultJson = new JSONObject();//返回的结果JSON
        JSONObject dataOfJson; //评测数据
        JSONObject readWord; //章节评测部分
        String is_rejected;
        int except_info;
        double score;
        DecimalFormat df = new DecimalFormat("#.00"); //定义小数点后两位的格式

        //获取json最外层对象
        JSONObject jsonObject = JSONObject.parseObject(json);
        dataOfJson = jsonObject.getJSONObject("data");//获取数据对象

        readWord = (JSONObject) JSONPath.eval(dataOfJson, "$.read_word.rec_paper.read_word");
        except_info = readWord.getInteger("except_info");
        is_rejected = readWord.getString("is_rejected");

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

        score = Double.parseDouble(df.format(readWord.getDouble("total_score")));
        resultJson.put("score", score);


        return resultJson.toJSONString();
    }
}
