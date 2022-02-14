package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.SentenceInfo;
import com.wx.speaking.bean.User;
import com.wx.speaking.bean.WordInfo;
import com.wx.speaking.mapper.SentenceInfoMapper;
import com.wx.speaking.mapper.UserMapper;
import com.wx.speaking.mapper.WordInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    WordInfoMapper wordInfoMapper;
    @Autowired
    SentenceInfoMapper sentenceInfoMapper;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String id){
        return userMapper.getUserById(id);
    }

    @PostMapping("/addUser")
    public void addUser(User user){
        userMapper.addUser(user);
    }

    @PutMapping("/updata")
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    @GetMapping("/getRank")
    public String getRank(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        double score=0;
        int count=0;
        List<WordInfo> userWord = wordInfoMapper.getUserWord(user_id);
        List<SentenceInfo> userSentence = sentenceInfoMapper.getUserSentence(user_id);
        if(userWord!=null){
            for (WordInfo wi:userWord){
                count += wi.getCount();
                score += wi.getTotalScore()*wi.getCount();

            }
        }
        if(userSentence!=null){
            for(SentenceInfo si:userSentence){
                score += si.getTotalScore()*si.getCount();
                count += si.getCount();
            }
        }
        if(count!=0){
            score /= count;
        }
        result.put("score", score);
        return result.toJSONString();
    }
}
