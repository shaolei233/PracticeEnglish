package com.wx.speaking.bean;

public class SentenceInfo {
    private String userId;
    private Integer sentenceId;
    private Double totalScore;
    private Double accuracyScore;
    private Double fluencyScore;
    private Double integrityScore;
    private Integer count;

    @Override
    public String toString() {
        return "SentenceInfo{" +
                "userId='" + userId + '\'' +
                ", sentenceId=" + sentenceId +
                ", accuracyScore=" + accuracyScore +
                ", fluencyScore=" + fluencyScore +
                ", integrityScore=" + integrityScore +
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

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
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

