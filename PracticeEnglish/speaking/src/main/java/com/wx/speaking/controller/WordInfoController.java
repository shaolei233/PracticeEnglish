package com.wx.speaking.controller;

import com.wx.speaking.bean.SentenceInfo;
import com.wx.speaking.mapper.SentenceInfoMapper;
import com.wx.speaking.mapper.WordInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@SpringBootApplication
public class WordInfoController {
    @Autowired
    SentenceInfoMapper sentenceInfoMapper;
    @PostMapping( "/mybatis_info")
    public void mybatisTest(HttpServletRequest request, HttpServletResponse response) {
//        SentenceInfo  sentenceInfo=new SentenceInfo("12",1,"acd",0.0,0.0,0.0,0.0);
//        sentenceInfoMapper.insertSentence_info( sentenceInfo);
//
    }
}
