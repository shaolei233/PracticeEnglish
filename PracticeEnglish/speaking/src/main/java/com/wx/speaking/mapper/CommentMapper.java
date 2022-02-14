package com.wx.speaking.mapper;

import com.wx.speaking.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommentMapper {

    //添加评论
    void insertComment(String user_id, Integer post_id, String content, Date time);

    //获取评论内容
    List<Comment> getComments(Integer post_id);
}
