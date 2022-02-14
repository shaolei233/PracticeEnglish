package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Stat;
import com.wx.speaking.mapper.SentenceInfoMapper;
import com.wx.speaking.mapper.SentenceMapper;
import com.wx.speaking.mapper.WordInfoMapper;
import com.wx.speaking.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatController {

    @Autowired
    WordMapper wordMapper;
    @Autowired
    WordInfoMapper wordInfoMapper;
    @Autowired
    SentenceMapper sentenceMapper;
    @Autowired
    SentenceInfoMapper sentenceInfoMapper;

    /**
     * input:user_id
     * output:{avgAllWordScore:, avgWordScore:, avgAllSentenceScore:, avgSentenceScore:}
     */
    @GetMapping("/getStat")
    public String getStat(HttpServletRequest request){

        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        List<Double> avgAllSentenceScore = new ArrayList<>();
        List<Double> avgSentenceScore = new ArrayList<>();
        Double avgAllWordScore = wordMapper.getAvg();
        Stat avgAllSentenceScoreDao = sentenceMapper.getAvg();

        Double avgWordScore;
        Stat avgSentenceScoreDao = sentenceInfoMapper.getAvg(user_id);
        DecimalFormat df = new DecimalFormat("#.00");

        if(wordInfoMapper.getAvg(user_id)==null){
            avgWordScore = 0.00;
        }else{
            avgWordScore = wordInfoMapper.getAvg(user_id);
            avgWordScore = Double.valueOf(df.format(avgWordScore));
        }

        if(avgSentenceScoreDao==null){
            for(int i=0; i<4; i++){
                avgSentenceScore.add(0.00);
            }
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getTotalScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getAccuracyScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getFluencyScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getIntegrityScore())));

        }else{
            avgSentenceScore.add(Double.valueOf(df.format(avgSentenceScoreDao.getTotalScore())));
            avgSentenceScore.add(Double.valueOf(df.format(avgSentenceScoreDao.getAccuracyScore())));
            avgSentenceScore.add(Double.valueOf(df.format(avgSentenceScoreDao.getFluencyScore())));
            avgSentenceScore.add(Double.valueOf(df.format(avgSentenceScoreDao.getIntegrityScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getTotalScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getAccuracyScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getFluencyScore())));
            avgAllSentenceScore.add(Double.valueOf(df.format(avgAllSentenceScoreDao.getIntegrityScore())));

        }
        avgAllWordScore = Double.valueOf(df.format(avgAllWordScore));

        result.put("avgAllWordScore", avgAllWordScore);
        result.put("avgWordScore", avgWordScore);
        result.put("avgAllSentenceScore", avgAllSentenceScore);
        result.put("avgSentenceScore", avgSentenceScore);

        return result.toJSONString();
    }
}
