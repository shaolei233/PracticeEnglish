package com.wx.speaking.mapper;

import com.wx.speaking.bean.Word;
import com.wx.speaking.bean.WordInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordInfoMapper {
    //插入用户的单词分数
    void insertWordInfo(String userId, Integer wordId, double score);

    //跟新用户的单词分数
    void updateWordInfo(String userId, Integer wordId, Double score);

    //查找用户的单词分数
    WordInfo getWordInfo(String userId, Integer wordId);

    //删除用户的单词分数
    void deleteWordInfo(String userId, Integer wordId);

    //查找列表
    Integer[] getWordList(String userId,int start, int end,int plan);

    Double getAvg(String userId);

    void setCollect(String userId, Integer id);

    void cancelCollect(String userId, Integer id);

    List<WordInfo> getCollect(String user_id);

    List<WordInfo> getUserWord(String user_id);
}
