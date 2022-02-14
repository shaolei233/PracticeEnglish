package com.wx.speaking.bean;

public class Word {

    private Integer id;
    private String word;
    private String videoPath;
    private Integer courseId;
    private Double score;
    private Integer flag;
    private Integer count;
    private String chinese;
    private String phoneticSign;


    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", courseId=" + courseId +
                ", score=" + score +
                ", flag=" + flag +
                ", count=" + count +
                ", chinese='" + chinese + '\'' +
                ", phoneticSign='" + phoneticSign + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double totalScore) {
        this.score = totalScore;
    }

    public Integer getFlag() { return flag; }

    public void setFlag(Integer flag) { this.flag = flag; }

    public Integer getCount() {
        return count;
    }

    public String getPhoneticSign() {
        return phoneticSign;
    }

    public void setPhoneticSign(String phoneticSign) {
        this.phoneticSign = phoneticSign;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
