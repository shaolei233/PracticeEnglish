package com.wx.speaking.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

@RestController
@SpringBootApplication
public class UserInfoController {

    @PostMapping( "/user_info")
    public void buttonTest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String user_name= request.getParameter("userName");
        String userSex= request.getParameter("userSex");
        String email= request.getParameter("email");
        String education= request.getParameter("education");
        String intro= request.getParameter("intro");



        System.out.println("user_name="+user_name+"  userSex="+userSex+"  email="+email
            +"  education="+education+"  intro="+intro+"  id="+id);

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write("进入后台");
        out.flush();
    }
}
