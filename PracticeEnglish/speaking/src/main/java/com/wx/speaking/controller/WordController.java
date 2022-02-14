package com.wx.speaking.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Word;
import com.wx.speaking.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WordController {

    @Autowired
    WordMapper wordMapper;


    @GetMapping("/getWord")
    public Word getWord(HttpServletRequest httpServletRequest){
        Integer id = Integer.valueOf(httpServletRequest.getParameter("id"));
        System.out.println("查单词");
        Word word = wordMapper.getWordById(id);
        return word;
    }

    @GetMapping("/getAllWord")
    public String getAllWord(){
        JSONObject result = new JSONObject();
        Map<Character, ArrayList<Word>> map = new HashMap<>();
        List<Word> wordList = wordMapper.getAllWord();
        for(Word word:wordList){
            char c;
            if(word.getWord().charAt(0)<97){
                c = word.getWord().charAt(0);
            }else{
                c = (char) (word.getWord().charAt(0)-32);
            }
            if(!map.containsKey(c)){
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(word);
        }

        result.put("wordList", map);
        return result.toString();
    }
//
//    @PostMapping("/updateWord")
//    public void updateWord(Word word){
//        wordMapper.updateWord(word);
//    }
}
