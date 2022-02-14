package com.wx.speaking.bean;

public class Course {

    private Integer id;//课程id

    private String name;//课程名

    private String courseImage;//课程图片

    private Integer start;

    private Integer end;

    private Integer type;

    private Integer difficulty;

    private Integer count;

    private String intro;


    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public Integer getStart() {
        return start;
    }


    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseImage='" + courseImage + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", type=" + type +
                ", difficulty=" + difficulty +
                ", count=" + count +
                ", intro='" + intro + '\'' +
                '}';
    }
}
