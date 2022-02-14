package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Post;
import com.wx.speaking.mapper.PostMapper;
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
public class PostController {

    @Autowired
    PostMapper postMapper;

    @PostMapping("/addPost")
    public String addPost(HttpServletRequest request) throws ParseException {
        String title = request.getParameter("title");
        String userId = request.getParameter("user_id");
        String content = request.getParameter("content");
        String time = request.getParameter("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);

        postMapper.addPost(title, userId, content, date, date);
        return "OK";
    }

    @GetMapping("/getPostList")
    public String getPost(HttpServletRequest request){
        List<Post> postList = postMapper.getPostList();
        JSONObject result = new JSONObject();
        result.put("list", postList);
        return result.toJSONString();
    }
}
