package com.wx.speaking.mapper;

import com.wx.speaking.bean.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface PostMapper {

    //添加新的帖子
    void addPost(String title, String user_id, String content, Date time, Date lastTime);

    //获取帖子列表
    List<Post> getPostList();

    //修改帖子最新评论的时间
    void updatePostTime(Integer id, Date lastTime);
}
