package com.wx.speaking.bean;

public class WordInfo {

    private String userId;
    private Integer wordId;
    private Double totalScore;
    private Integer count;

    @Override
    public String toString() {
        return "WordInfo{" +
                "userId='" + userId + '\'' +
                ", wordId=" + wordId +
                ", totalScore=" + totalScore +
                ", count=" + count +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
