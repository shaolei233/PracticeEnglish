package com.wx.speaking.mapper;

import com.wx.speaking.bean.Sentence;
import com.wx.speaking.bean.Stat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SentenceMapper {

    public Sentence getSentenceById(Integer id);

    public void updateSentence(Integer id, Double totalScore, Double accuracyScore,
                               Double fluencyScore, Double integrityScore, String wordScore);

    public List<Sentence> getAllSentence();

    public List<Sentence> getSentenceList(Integer courseId);

    public Stat getAvg();


}
