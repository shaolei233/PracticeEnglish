package com.wx.speaking.services;

import com.alibaba.fastjson.JSONObject;
import com.wx.speaking.bean.Course;
import com.wx.speaking.bean.Sign;
import com.wx.speaking.bean.User;
import com.wx.speaking.mapper.CourseMapper;
import com.wx.speaking.mapper.SignMapper;
import com.wx.speaking.mapper.UserCourseMapper;
import com.wx.speaking.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    @Autowired
    SignMapper signMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserCourseMapper userCourseMapper;

    public String getSign(String user_id){
        JSONObject result = new JSONObject();
        Sign sign = signMapper.getSignById(user_id);
        User user = userMapper.getUserById(user_id);
        Integer curCourse = userMapper.getCurCourse(user_id);
        if(curCourse==-1){
            result.put("daysToLearn", 0);
        }else{
            Course course = courseMapper.getCourseByCourseId(curCourse);
            int start = course.getStart();
            int end = course.getEnd();
            int curProcess = userCourseMapper.getCurProcess(user_id, user.getCurCourse());
            int plan = userCourseMapper.getPlan(user_id, user.getCurCourse());
            int days = (end-curProcess+1)/plan;
            result.put("daysToLearn", days);
        }
        result.put("sign", sign);
        return result.toJSONString();
    }
}
