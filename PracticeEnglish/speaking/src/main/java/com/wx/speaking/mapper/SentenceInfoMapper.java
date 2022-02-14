package com.wx.speaking.mapper;

import com.wx.speaking.bean.SentenceInfo;
import com.wx.speaking.bean.Stat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SentenceInfoMapper {
    //插入用户的句子分数
    void insertSentenceInfo(String userId, Integer sentenceId, Double totalScore,
                            Double accuracyScore, Double fluencyScore, Double integrityScore);

    //更新用户的句子分数
    void updateSentenceInfo(String userId, Integer sentenceId, Double totalScore,
                            Double accuracyScore, Double fluencyScore, Double integrityScore);

    //查找用户的句子分数
    SentenceInfo getSentenceInfo(String userId, Integer sentenceId);

    //删除用户的句子分数
    void deleteSentenceInfo(String userId, Integer sentenceId);

    //查找列表
    Integer[] getSentenceList(String userId,int start, int end,int plan);

    Stat getAvg(String userId);

    void setCollect(String userId, Integer id);

    void cancelCollect(String userId, Integer id);

    List<SentenceInfo> getCollect(String user_id);

    List<SentenceInfo> getUserSentence(String user_id);
}
