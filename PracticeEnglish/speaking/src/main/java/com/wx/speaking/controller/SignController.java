package com.wx.speaking.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Course;
import com.wx.speaking.bean.Sign;
import com.wx.speaking.bean.User;
import com.wx.speaking.mapper.CourseMapper;
import com.wx.speaking.mapper.SignMapper;
import com.wx.speaking.mapper.UserCourseMapper;
import com.wx.speaking.mapper.UserMapper;
import com.wx.speaking.services.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SignController {
    @Autowired
    SignMapper signMapper;

    @Autowired
    SignService signService;

    @Autowired
    UserCourseMapper userCourseMapper;

    @PostMapping("/sign")
    public String signIn(HttpServletRequest request){
        JSONObject result = new JSONObject();
        String user_id = request.getParameter("user_id");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now_date = format.format(date);
        signMapper.updateSignByUser(user_id, now_date);
        Sign sign = signMapper.getSignById(user_id);
        userCourseMapper.updateCurProcess(user_id);

        result.put("sign", sign);
        return result.toJSONString();
    }

    @GetMapping("/getSign")
    public String getSign(HttpServletRequest request){

        String user_id = request.getParameter("user_id");
        return signService.getSign(user_id);
    }

}
