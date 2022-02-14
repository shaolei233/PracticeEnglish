package com.wx.speaking.bean;

public class Sentence {

    private Integer id;
    private String content;
    private String videoPath;
    private Integer courseId;
    private Double accuracyScore;
    private Double fluencyScore;
    private Double integrityScore;
    private String wordScore;
    private Double totalScore;
    private Integer flag;
    private Integer count;
    private String chinese;

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", courseId=" + courseId +
                ", accuracyScore=" + accuracyScore +
                ", fluencyScore=" + fluencyScore +
                ", integrityScore=" + integrityScore +
                ", wordScore='" + wordScore + '\'' +
                ", totalScore=" + totalScore +
                ", flag=" + flag +
                ", count=" + count +
                ", chinese='" + chinese + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Double getAccuracyScore() {
        return accuracyScore;
    }

    public void setAccuracyScore(Double accuracyScore) {
        this.accuracyScore = accuracyScore;
    }

    public Double getFluencyScore() {
        return fluencyScore;
    }

    public void setFluencyScore(Double fluencyScore) {
        this.fluencyScore = fluencyScore;
    }

    public Double getIntegrityScore() {
        return integrityScore;
    }

    public void setIntegrityScore(Double integrityScore) {
        this.integrityScore = integrityScore;
    }

    public String getWordScore() {
        return wordScore;
    }

    public void setWordScore(String wordScore) {
        this.wordScore = wordScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
