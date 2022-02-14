package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.*;
import com.wx.speaking.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@SpringBootApplication
public class PracticeController {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    WordMapper wordMapper;
    @Autowired
    WordInfoMapper wordInfoMapper;
    @Autowired
    SentenceInfoMapper sentenceInfoMapper;
    @Autowired
    SentenceMapper sentenceMapper;

    @PostMapping("/getWord")
    public void getWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        Integer wordId =Integer.parseInt( request.getParameter("id"));
        Integer courseId =Integer.parseInt( request.getParameter("courseId"));
        String openId = request.getParameter("openId");
        System.out.println(openId);
        Course course = courseMapper.getCourseByCourseId(courseId);
        int end=course.getEnd();
        int start=course.getStart();
        if(wordId>end){
            wordId=-2;
        }else if(wordId<start){
            wordId=-1;
        }else{
            WordInfo wordInfo = wordInfoMapper.getWordInfo(openId,wordId);
            Word word =wordMapper.getWordById(wordId);
            if(wordInfo!=null){
                result.put("myAvgScore",wordInfo.getTotalScore());
            }
            else{
                result.put("myAvgScore",0);
                wordInfoMapper.insertWordInfo(openId, wordId, 0.0);
            }
            result.put("word",word.getWord());
            result.put("mean",word.getChinese());
            result.put("avgScore",word.getScore());
            result.put("phoneticSign", word.getPhoneticSign());
        }
        result.put("curProcess",wordId);
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("getword");
        Writer out = response.getWriter();
        out.write(String.valueOf(result));
        out.flush();
    }

    @PostMapping("/getSentence")
    public void getSentence(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        Integer sentenceId =Integer.parseInt( request.getParameter("sentenceId"));
        Integer courseId =Integer.parseInt( request.getParameter("courseId"));
        Course course = courseMapper.getCourseByCourseId(courseId);
        String openId = request.getParameter("openId");
        int end=course.getEnd();
        int start=course.getStart();
        if(sentenceId>end){
            sentenceId=-2;
        }else if(sentenceId<start){
            sentenceId=-1;
        }else{
            SentenceInfo sentenceInfo = sentenceInfoMapper.getSentenceInfo(openId,sentenceId);
            Sentence sentence = sentenceMapper.getSentenceById(sentenceId);
            if(sentenceInfo!=null) {
                result.put("myAvgaccuracyScore",sentenceInfo.getAccuracyScore());
                result.put("myAvgfluencyScore",sentenceInfo.getFluencyScore());
                result.put("myAvgintegrityScore",sentenceInfo.getIntegrityScore());
                result.put("myAvgScore",sentenceInfo.getTotalScore());
            } else {
                result.put("myAvgaccuracyScore",0.0);
                result.put("myAvgfluencyScore",0.0);
                result.put("myAvgintegrityScore",0.0);
                result.put("myAvgScore",0.0);
                sentenceInfoMapper.insertSentenceInfo(openId, sentenceId, 0.0, 0.0, 0.0, 0.0);
            }
            result.put("sentence",sentence.getContent());
            result.put("mean",sentence.getChinese());
            result.put("avgScore",sentence.getTotalScore());
            result.put("avgaccuracyScore",sentence.getAccuracyScore());
            result.put("avgfluencyScore",sentence.getFluencyScore());
            result.put("avgintegrityScore",sentence.getIntegrityScore());
        }
        result.put("curProcess",sentenceId);
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("getsentence");
        Writer out = response.getWriter();
        out.write(String.valueOf(result));
        out.flush();
    }

    @PostMapping("/getList")
    public void getList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        Integer courseId =Integer.parseInt( request.getParameter("courseId"));
        Course course = courseMapper.getCourseByCourseId(courseId);
        Integer processId = Integer.parseInt( request.getParameter("processId"));
        String openId = request.getParameter("openId");
        Integer plan = Integer.parseInt( request.getParameter("plan"));
        int end=course.getEnd();
        int start=course.getStart();
        if(processId>end){
            processId=-2;
        }else if(processId<start){
            processId=-1;
        }else{
            if(processId-plan>=start){
               if(course.getType()==1){
                   Integer []list1 =wordInfoMapper.getWordList(openId,processId-plan,processId,plan/2);
                   ArrayList<Integer> list = new ArrayList<>( Arrays.asList(list1));
                   if(end-processId>=plan){
                       for(int i=1;i<=plan;i++) list.add(processId+i);
                   }else{
                       for(int i=processId+1;i<end;i++) list.add(i);
                   }
                   result.put("list",list);
               }else{
                   Integer []list1 =sentenceInfoMapper.getSentenceList(openId,processId-plan,processId,plan/2);
                   ArrayList<Integer> list = new ArrayList<>( Arrays.asList(list1));
                   if(end-processId>=plan){
                       for(int i=1;i<=plan;i++) list.add(processId+i);
                   }else{
                       for(int i=processId+1;i<plan;i++) list.add(i);
                   }
                   result.put("list",list);
               }
            }else {
                    ArrayList<Integer> list = new ArrayList<>();
                    if(end-processId>=plan){
                        for(int i=0;i<=plan;i++) list.add(processId+i);
                    }else{
                        for(int i=processId;i<=end;i++) list.add(i);
                    }
                    result.put("list",list);
            }

        }
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("getList");
        Writer out = response.getWriter();
        out.write(String.valueOf(result));
        out.flush();
    }

    @PostMapping("/setCollect")
    public void setCollect(HttpServletRequest request){
        String userId = request.getParameter("user_id");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer type = Integer.valueOf(request.getParameter("type"));

        if(type==1){
            wordInfoMapper.setCollect(userId, id);
        }else {
            sentenceInfoMapper.setCollect(userId, id);
        }
    }

    @PostMapping("/cancelCollect")
    public void cancelCollect(HttpServletRequest request){
        String userId = request.getParameter("user_id");
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer type = Integer.valueOf(request.getParameter("type"));

        if(type==1){
            wordInfoMapper.cancelCollect(userId, id);
        }else {
            sentenceInfoMapper.cancelCollect(userId, id);
        }
    }
}
