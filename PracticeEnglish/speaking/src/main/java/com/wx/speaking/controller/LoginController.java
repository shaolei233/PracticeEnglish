package com.wx.speaking.controller;

import com.wx.speaking.bean.Course;
import com.wx.speaking.bean.Sign;
import com.wx.speaking.bean.User;
import com.wx.speaking.bean.Word;
import com.wx.speaking.mapper.*;
import com.wx.speaking.services.SignService;
import com.wx.utils.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import com.alibaba.fastjson.*;

@RestController
@SpringBootApplication
public class LoginController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCourseMapper userCourseMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    WordMapper wordMapper;
    @Autowired
    SignMapper signMapper;
    @Autowired
    SignService signService;


    @PostMapping( "/login")
    public void buttonTest(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        JSONObject result = new JSONObject();
        String code= request.getParameter("code");
        String name= request.getParameter("userName");
        String sex= request.getParameter("userSex");
        System.out.println(code);
        System.out.println(name);
        System.out.println(sex);
        //高彤
        String wxspAppid = "wx0db9e901dad76940";
        String wxspSecret = "c8b6fff3b7ade152ba55e747a48f275d";


        //郭锦宏
//        String wxspAppid = "wxb97570a7fad1ffbe";
//        String wxspSecret = "9f92cbb76b11d5a311109fcc526900d6";


        //林经韬
//        String wxspAppid = "wx91537efecabcb020";
//        String wxspSecret = "2bbc00bfc307ec9bf7a8d6db5ec82e99";


        //彭文俊
//        String wxspAppid = "wx47c47284f2c5cf87";
//        String wxspSecret = "01185084a6d26c743e22cedd398d8db9";
        // 授权（必填）
        String grant_type = "authorization_code";

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）

        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
//        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        System.out.println(openid);
        result.put("openId", openid);
        User user =userMapper.getUserById(openid);
        System.out.println(user==null);
        if(user==null){
            User user1 = new User(openid,name,sex,-1);
            userMapper.addUser(user1);
            signMapper.addSignUser(openid);
            userCourseMapper.addUserCourse(openid, 0, 0);
            System.out.println(111);
            result.put("curProcess",-1);
            result.put("courseId", -1);
            String sign = signService.getSign(openid);
            result.put("signInfo", sign);

        }else{
            if(user.getCurCourse()==-1){
                result.put("courseId", -1);
                result.put("curProcess",-1);
                String sign = signService.getSign(openid);
                result.put("signInfo", sign);

            }else{
                Integer curProcess=userCourseMapper.getCurProcess(user.getId(),user.getCurCourse());
                Course course=courseMapper.getCourseByCourseId(user.getCurCourse());
                String sign = signService.getSign(user.getId());
                Integer plan = userCourseMapper.getPlan(user.getId(), user.getCurCourse());
                int end=course.getEnd();
                if(curProcess==end){
                    curProcess=-1;
                }else{
                    result.put("courseId",user.getCurCourse());
                    result.put("courseType",course.getType());
                    result.put("signInfo", sign);
                    result.put("plan", plan);
                }
                result.put("curProcess",curProcess);
            }

        }
        //返回值给微信小程序
        response.setContentType("text/html;charset=UTF-8");
        Writer out = response.getWriter();
        out.write(String.valueOf(result));
        out.flush();
    }

}
