package com.wx.speaking.mapper;

import com.wx.speaking.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //用户查看信息
    public User getUserById(String id);

    //用户第一次登陆时需要插入信息
    public void addUser(User user);

    //用户修改信息
    public void updateUser(User user);

    //获取当前课程
    public Integer getCurCourse(String id);

    //更新课程
    public void updateUserCourse(String user_id, Integer course_id);

}
