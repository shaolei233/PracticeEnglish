package com.wx.speaking.bean;

public class Stat {

    private Double totalScore;
    private Double accuracyScore;
    private Double fluencyScore;
    private Double integrityScore;

    @Override
    public String toString() {
        return "Stat{" +
                "totalScore=" + totalScore +
                ", accuracyScore=" + accuracyScore +
                ", fluencyScore=" + fluencyScore +
                ", integrityScore=" + integrityScore +
                '}';
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
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
}
