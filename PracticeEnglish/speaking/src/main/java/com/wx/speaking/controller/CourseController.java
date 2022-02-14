package com.wx.speaking.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.wx.speaking.bean.*;
import com.wx.speaking.mapper.*;
import com.wx.speaking.services.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    WordMapper wordMapper;

    @Autowired
    SentenceMapper sentenceMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    @Autowired
    WordInfoMapper wordInfoMapper;

    @Autowired
    SentenceInfoMapper sentenceInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SignService signService;

    @PostMapping("/addCourse")
    public void addCourse(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        System.out.println(user_id + course_id);
        courseMapper.addUserCourse(user_id, course_id);
    }

    @GetMapping("/getCourseList")
    public List<Course> getAllCourse(HttpServletRequest request){
        JSONArray res = new JSONArray();
        List<Course> courseList = courseMapper.getAllCourse();
        return courseList;
    }

    @GetMapping("/getCourseById")
    public String getCourseById(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        Course course =  courseMapper.getCourseByCourseId(course_id);
        result.put("course_info", course);
        if (userCourseMapper.getCourseByUserAndCourseId(user_id, course_id)==null){
            result.put("is_chosen", 0);
            return result.toJSONString();
        }else{
            result.put("is_chosen", 1);
            return result.toJSONString();
        }

    }

    @GetMapping("/getWordList")
    public String getWordListById(HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map<Character, ArrayList<Word>> map = new HashMap<>();
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        List<Word> wordList = wordMapper.getWordList(course_id);
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

    @GetMapping("/getSentenceList")
    public String getSentenceListById(HttpServletRequest request){
        JSONObject result = new JSONObject();
        Map<Character, ArrayList<Sentence>> map = new HashMap<>();
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        List<Sentence> sentenceList = sentenceMapper.getSentenceList(course_id);
        for(Sentence sentence:sentenceList){
            char c = (char) (sentence.getContent().charAt(0));
            if(!map.containsKey(c)){
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(sentence);
        }
        result.put("sentenceList", map);
        return result.toString();
    }

    @GetMapping("/getCourseByUserId")
    public List<Course> getCourseByUserId(HttpServletRequest request){
        String id = request.getParameter("user_id");
        return courseMapper.getCourseByUserId(id);
    }

    /**
     * 更新用户当前课程
     */
    @PostMapping("/selectCourse")
    public String updateUserCourse(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        Integer curProcess;
        Integer plan = Integer.valueOf(request.getParameter("plan"));

        //获取课程第一个单词位置
        Course course = courseMapper.getCourseByCourseId(course_id);
        int end = course.getEnd();
        curProcess = course.getStart();
        System.out.println(user_id);
        userCourseMapper.updateUserCourse(user_id, course_id, curProcess, plan);
        userMapper.updateUserCourse(user_id, course_id);
        String sign = signService.getSign(user_id);
        result.put("curProcess", curProcess);
        result.put("type", course.getType());
        result.put("signInfo", sign);
        return result.toString();
    }

    /**
     * 获取收藏单词
     * @param request
     * @return String
     */
    @GetMapping("/getWordCollect")
    public String getWordCollect(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        HashMap<Character, ArrayList<Word>> map = new HashMap<>();

        List<WordInfo> wordCollection = wordInfoMapper.getCollect(user_id);

        if(wordCollection!=null){
            for(WordInfo wordInfo:wordCollection){
                int id = wordInfo.getWordId();
                Word word = wordMapper.getWordById(id);
                char c = (char) (word.getWord().charAt(0)-32);
                if(!map.containsKey(c)){
                    map.put(c, new ArrayList<>());
                }
                map.get(c).add(word);
            }
            result.put("collection", map);
        }

        return result.toJSONString();
    }

    @GetMapping("/getSentenceCollect")
    public String getSentenceCollect(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        HashMap<Character, ArrayList<Sentence>> map = new HashMap<>();

        List<SentenceInfo> sentenceCollection = sentenceInfoMapper.getCollect(user_id);

        if(sentenceCollection!=null){
            for(SentenceInfo sentenceInfo:sentenceCollection){
                int id = sentenceInfo.getSentenceId();
                Sentence sentence = sentenceMapper.getSentenceById(id);
                char c = (char) (sentence.getContent().charAt(0));
                if(!map.containsKey(c)){
                    map.put(c, new ArrayList<>());
                }
                map.get(c).add(sentence);
            }
            result.put("collection", map);
        }

        return result.toJSONString();
    }

    @GetMapping("/getMyCourse")
    public String getMyCourse(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        Integer course_id = Integer.valueOf(request.getParameter("course_id"));
        Integer curProcess = userCourseMapper.getCurProcess(user_id, course_id);
        Course course = courseMapper.getCourseByCourseId(course_id);
        String courseName = course.getName();
        Integer process = curProcess-course.getStart();
        Integer count = course.getEnd()-course.getStart()+1;

        result.put("course_name", courseName);
        result.put("process", process);
        result.put("count", count);
        return result.toJSONString();

    }

}
