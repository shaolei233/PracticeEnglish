package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Sentence;
import com.wx.speaking.bean.SentenceInfo;
import com.wx.speaking.bean.Word;
import com.wx.speaking.bean.WordInfo;
import com.wx.speaking.mapper.SentenceInfoMapper;
import com.wx.speaking.mapper.SentenceMapper;
import com.wx.speaking.mapper.WordInfoMapper;
import com.wx.speaking.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ContentController {

    @Autowired
    WordMapper wordMapper;

    @Autowired
    WordInfoMapper wordInfoMapper;

    @Autowired
    SentenceMapper sentenceMapper;

    @Autowired
    SentenceInfoMapper sentenceInfoMapper;

    @GetMapping("/getContent")
    public String getContent(HttpServletRequest request){
        String userId = request.getParameter("user_id");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer type = Integer.valueOf(request.getParameter("type"));
        JSONObject result = new JSONObject();

        if(type == 0){
            Word word = wordMapper.getWordById(id);
            WordInfo wordInfo = wordInfoMapper.getWordInfo(userId, id);
            result.put("content", word);
            result.put("contentInfo", wordInfo);
            return result.toJSONString();
        }else{
            Sentence sentence = sentenceMapper.getSentenceById(id);
            SentenceInfo sentenceInfo = sentenceInfoMapper.getSentenceInfo(userId, id);
            result.put("content",sentence);
            result.put("contentInfo", sentenceInfo);
            return result.toJSONString();
        }
    }
}
