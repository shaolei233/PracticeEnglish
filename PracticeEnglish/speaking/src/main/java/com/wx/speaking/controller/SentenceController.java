package com.wx.speaking.controller;
import com.alibaba.fastjson.*;
import com.wx.speaking.bean.Sentence;
import com.wx.speaking.bean.Word;
import com.wx.speaking.mapper.SentenceMapper;
import com.wx.speaking.services.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SentenceController {

    @Autowired
    SentenceService sentenceService;
    @Autowired
    SentenceMapper sentenceMapper;

    @GetMapping("/getAllSentence")
    public String getAllSentence(){
        JSONObject result = new JSONObject();
        Map<Character, ArrayList<Sentence>> map = new HashMap<>();
        List<Sentence> sentenceList = sentenceMapper.getAllSentence();
        for(Sentence sentence:sentenceList){
            char c = sentence.getContent().charAt(0);
            if(!map.containsKey(c)){
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(sentence);
        }
        result.put("sentenceList", map);
        return result.toString();
    }
}
