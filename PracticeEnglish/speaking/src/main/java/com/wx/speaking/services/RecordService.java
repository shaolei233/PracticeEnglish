package com.wx.speaking.services;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.controller.SentenceController;
import com.wx.utils.FileUtil;
import com.wx.utils.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecordService {
    @Autowired
    SentenceService sentenceService;

    @Autowired
    WordService wordService;

    // 合成webapi接口地址
    private static final String WEBISE_URL = "https://api.xfyun.cn/v1/service/v1/ise";
    // 应用ID
    private static final String APPID = "5ece1082";
    // 接口密钥
    private static final String API_KEY = "458c0a3a57e3f6ce89ae73f0b6af59a9";
    // 音频编码
    private static final String AUE = "raw";
    // 采样率
    private static final String AUF = "audio/L16;rate=16000";
    // 结果级别
    private static final String RESULT_LEVEL = "entirety";
    // 语种
    private static final String LANGUAGE = "en_us";
    // 评测种类
    private static String CATEGORY = "";
    // 音频文件地址
    private static String AUDIO_PATH = "";
    // 评测文本
    private static String TEXT = "";
    //全维度
    private static String EXTRA_ABILITY = "multi_dimension";

    /**
     * 评测 WebAPI 调用示例程序
     */
    public String callApi(String category, String audioPath, String text, Integer type, String user_id, Integer id) throws IOException {
        CATEGORY = category;
        AUDIO_PATH = audioPath;
        TEXT = text;
        Map<String, String> header = buildHttpHeader(CATEGORY);
        byte[] audioByteArray = FileUtil.read(AUDIO_PATH);
        String audioBase64 = new String(Base64.encodeBase64(audioByteArray), "UTF-8");
        String result = HttpUtil.doPost1(WEBISE_URL, header, "audio=" + URLEncoder.encode(audioBase64, "UTF-8") + "&text=" + URLEncoder.encode(TEXT, "UTF-8"));
        System.out.println("评测 WebAPI 接口调用结果：" + result);
//        ResultController.resolveJson(result);
        if(type==1){
            String s = wordService.resolveJson(result);
            System.out.println(s);
            //应该判断一下分数是否有效
            JSONObject res = JSONObject.parseObject(s);
            if(res.getInteger("info")!=0){
                return s;
            }else{
                if(res.getString("msg").equals("rejected")){
                    return s;
                }else {
                    wordService.addResultToDB(s, user_id, id);
                    return s;
                }
            }

        }else{
            String s = sentenceService.resolveJson(result);
            JSONObject res = JSONObject.parseObject(s);
            if(res.getInteger("info")!=0){
                return s;
            }else{
                if(res.getString("msg").equals("rejected")){
                    return s;
                }else {
                    sentenceService.addResultToDB(s, user_id, id);
                    return s;
                }
            }
        }

    }

    /**
     * 组装http请求头
     */
    private Map<String, String> buildHttpHeader(String category) throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"auf\":\"" + AUF + "\",\"aue\":\"" + AUE + "\",\"result_level\":\"" + RESULT_LEVEL + "\",\"language\":\"" + LANGUAGE + "\",\"category\":\"" + category + "\",\"extra_ability\":\"" + EXTRA_ABILITY + "\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }
}
