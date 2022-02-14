package com.wx.speaking.controller;


import com.wx.speaking.SpeakingApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@RestController
public class helloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello spring boot!";
    }

}
