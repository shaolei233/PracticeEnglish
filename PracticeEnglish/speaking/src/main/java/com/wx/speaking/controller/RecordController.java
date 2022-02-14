package com.wx.speaking.controller;

import com.wx.speaking.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class RecordController {

    @Autowired
    RecordService recordService;

    @PostMapping("/sendResult")
    public String sendResult(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
//        String savePath = "F:\\J2EE\\speaking\\src\\main\\resources\\record";
        String savePath = "/file/tmp/";
        String user_id = request.getParameter("user_id");
        String content = request.getParameter("content");
        Integer type = Integer.valueOf(request.getParameter("type"));
        Integer id = Integer.valueOf(request.getParameter("id"));

        //获取上传文件
        MultipartFile recordFile = req.getFile("wx_record");
        System.out.println(user_id);

        String desFile = savePath + recordFile.getOriginalFilename();

        File file = new File(desFile);
        recordFile.transferTo(file);
        System.out.println(file);

        if(type==1){
            String textPath = "[word]"+ content;
            String result = recordService.callApi("read_word", file.toString(), textPath, type, user_id, id);
            return result;
        }else{
            String textPath = "[content]"+ content;
            System.out.println(textPath);
            String result = recordService.callApi("read_sentence", file.toString(), textPath, type, user_id, id);
            return result;
        }

    }
}
