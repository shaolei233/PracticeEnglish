package com.wx.speaking.services;

import com.wx.speaking.bean.Sentence;
import com.wx.speaking.bean.SentenceInfo;
import com.wx.speaking.bean.Word;
import com.wx.speaking.bean.WordInfo;
import com.wx.speaking.mapper.SentenceInfoMapper;
import com.wx.speaking.mapper.SentenceMapper;
import com.wx.speaking.mapper.WordInfoMapper;
import com.wx.speaking.mapper.WordMapper;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class AsyncService {

    @Autowired
    WordMapper wordMapper;
    @Autowired
    WordInfoMapper wordInfoMapper;
    @Autowired
    SentenceMapper sentenceMapper;
    @Autowired
    SentenceInfoMapper sentenceInfoMapper;

    @Async
    public void updateWordInfo(String id, Integer word_id, Double score){
        Double newScore;
        Double prevScore;
        Integer count;
        //查询DB中是否有此条记录
        WordInfo wordInfo = wordInfoMapper.getWordInfo(id, word_id);

        prevScore = wordInfo.getTotalScore();
        count = wordInfo.getCount();
        newScore = ((prevScore*count)+score)/(count+1);
        wordInfoMapper.updateWordInfo(id, word_id, newScore);

        System.out.println("更新完成");
    }

    @Async
    public void updateWord(Integer word_id, Double score){
        Double newScore;
        Double prevScore;
        Integer count;

        Word word = wordMapper.getWordById(word_id);
        prevScore = word.getScore();
        count = word.getCount();
        newScore = ((prevScore*count)+score)/(count+1);
        wordMapper.updateWord(word_id, newScore);
    }

    @Async
    public void updateSentence(Integer id, Double totalScore, Double accuracyScore,
                               Double fluencyScore, Double integrityScore, String wordScore){
        Double newAccuracyScore;
        Double newFluencyScore;
        Double newIntegrityScore;
        Double newTotalScore;
        String[] prevWordScore;
        String[] newWordScore;
        String res;
        int count;

        DecimalFormat df = new DecimalFormat("0.00");
        Sentence sentence = sentenceMapper.getSentenceById(id);
        count = sentence.getCount();
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if(sentence.getWordScore()==null){
            res = wordScore;
        }else{
            wordScore = wordScore.substring(1, wordScore.length()-1);
            String tmp = sentence.getWordScore().substring(1, sentence.getWordScore().length()-1);
            prevWordScore = tmp.split(",");
            newWordScore = wordScore.split(",");
            for(int i=0; i<prevWordScore.length; i++){
                double prevScore = Double.parseDouble(prevWordScore[i]);
                double newScore = Double.parseDouble(newWordScore[i]);
                double result = Double.parseDouble(df.format((prevScore*count+newScore)/(count+1)));
                sb.append(String.valueOf(result)).append(",");
            }
            res = sb.deleteCharAt(sb.length()-1).append("]").toString();
        }


        double prevTotalScore = sentence.getTotalScore();
        double prevAccuracyScore = sentence.getAccuracyScore();
        double prevFluencyScore = sentence.getFluencyScore();
        double prevIntegrityScore = sentence.getIntegrityScore();

        newTotalScore = (prevTotalScore*count+totalScore)/(count+1);
        newAccuracyScore = (prevAccuracyScore*count+accuracyScore)/(count+1);
        newFluencyScore = (prevFluencyScore*count+fluencyScore)/(count+1);
        newIntegrityScore = (prevIntegrityScore*count+integrityScore)/(count+1);

        sentenceMapper.updateSentence(id, newTotalScore,newAccuracyScore, newFluencyScore, newIntegrityScore, res);
    }

    @Async
    public void updateSentenceInfo(String user_id, Integer id, Double totalScore, Double accuracyScore,
                                   Double fluencyScore, Double integrityScore){

        SentenceInfo sentenceInfo = sentenceInfoMapper.getSentenceInfo(user_id, id);
        int count = sentenceInfo.getCount();
        double newTotalScore = (sentenceInfo.getTotalScore()*count+totalScore)/(count+1);
        double newAccuracyScore = (sentenceInfo.getAccuracyScore()*count+accuracyScore)/(count+1);
        double newFluencyScore = (sentenceInfo.getFluencyScore()*count+fluencyScore)/(count+1);
        double newIntegrityScore = (sentenceInfo.getIntegrityScore()*count+integrityScore)/(count+1);

        sentenceInfoMapper.updateSentenceInfo(user_id, id, newTotalScore,
                newAccuracyScore, newFluencyScore, newIntegrityScore);


    }

}
