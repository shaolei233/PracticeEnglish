package com.wx.speaking.mapper;

import com.wx.speaking.bean.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface SignMapper {

    void addSignUser(String userId);

    Sign getSignById(String userId);

    void updateSignByUser(String userId, String newDate);

    void updateSignByTask(String newDate);

}
