package com.wx.speaking.services;

import com.wx.speaking.bean.Course;
import com.wx.speaking.bean.User;
import com.wx.speaking.bean.Word;
import com.wx.speaking.mapper.CourseMapper;
import com.wx.speaking.mapper.UserCourseMapper;
import com.wx.speaking.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;



public class GetRecourceService {

    @Autowired
    UserCourseMapper userCourseMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    WordMapper wordMapper;

//    public Integer getcurProcess(User user){
//        Integer curProcess=userCourseMapper.getCurProcess(user.getId(),user.getCurCourse());
//        Course course=courseMapper.getCourseByUserId(user.getCurCourse());
//        int end=course.getEnd();
//        if(curProcess!=end){
//            return curProcess;
//        }else return -1;
//    }
    public Word getWordById(Integer wordId){
        Word word =wordMapper.getWordById(wordId);
        return word;
    }
}
