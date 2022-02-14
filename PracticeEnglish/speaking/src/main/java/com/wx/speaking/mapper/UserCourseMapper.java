package com.wx.speaking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCourseMapper {

    Integer getCourseByUserId(String id);


    Integer getCurProcess(String user_id,Integer course_id);

    void addUserCourse(String user_id,Integer course_id,Integer curProcess);

    Integer getCourseByUserAndCourseId(String id, Integer course_id);

    Integer getPlan(String user_id,Integer course_id);

    void updateUserCourse(String user_id, Integer course_id, Integer curProcess, Integer plan);

    void updateCurProcess(String user_id);
}
