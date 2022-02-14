package com.wx.speaking.services;

import com.wx.speaking.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class TaskService {

    @Autowired
    SignMapper signMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSignTask(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String now_date = format.format(date);

        signMapper.updateSignByTask(now_date);
    }
}
