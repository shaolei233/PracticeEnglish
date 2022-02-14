package com.wx.speaking.controller;

import com.wx.speaking.bean.Sign;
import com.wx.speaking.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TestController {

    @Autowired
    SignMapper signMapper;

    @GetMapping("/test")
    public void test(){
        Sign sign = signMapper.getSignById("okUp55B47c84WSoGM03oBx5AYoN8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lastSignDate = sign.getLastSignDate();
        Date now = new Date();
        String lastDate = sdf.format(lastSignDate);
        String nowDate = sdf.format(now);
        if(lastDate.equals(nowDate)){
            System.out.println("1");
        }else{
            System.out.println("0");
        }

    }
}
