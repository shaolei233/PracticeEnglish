package com.wx.speaking.mapper;

import com.wx.speaking.bean.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper {

    public Word getWordById(Integer word_id);

    public List<Word> getWordList(Integer courseId);

    public void updateWord(Integer word_id, Double score);

    public List<Word> getAllWord();

    public Double getAvg();
}
