package com.wx.speaking.mapper;

import com.wx.speaking.bean.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

     void addUserCourse(String user_id, Integer course_id);

     List<Course> getCourseByUserId(String user_id);

     Course getCourseByCourseId(Integer courseId);

     List<Course> getAllCourse();


}
