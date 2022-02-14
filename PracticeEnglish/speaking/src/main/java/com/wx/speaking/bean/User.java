package com.wx.speaking.bean;

public class User {

    private String id;
    private String userName;
    private String userSex;
    private Integer curCourse;

    public Integer getCurCourse() {
        return curCourse;
    }

    public void setCurCourse(Integer curCourse) {
        this.curCourse = curCourse;
    }


    public User(String id, String userName, String userSex,Integer curCourse) {
        this.id = id;
        this.userName = userName;
        this.userSex = userSex;
        this.curCourse=curCourse;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

}
