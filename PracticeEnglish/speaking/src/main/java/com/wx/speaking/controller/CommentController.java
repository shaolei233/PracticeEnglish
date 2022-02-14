package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Comment;
import com.wx.speaking.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request) throws ParseException {
        String userId = request.getParameter("user_id");
        Integer postId = Integer.valueOf(request.getParameter("post_id"));
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        commentMapper.insertComment(userId, postId, content, date);
        return "添加成功";
    }

    @GetMapping("/getComments")
    public String getComments(HttpServletRequest request){
        Integer postId = Integer.valueOf(request.getParameter("post_id"));
        List<Comment> comments = commentMapper.getComments(postId);
        JSONObject result = new JSONObject();
        result.put("comments", comments);
        return result.toJSONString();
    }
}
