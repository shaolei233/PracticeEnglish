package com.wx.speaking.bean;


import java.util.Date;

public class Sign {
    private Integer id;
    private String userId;
    private Date LastSignDate;
    private Integer totalSignCount;
    private Integer LastSignCount;

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", LastSignDate=" + LastSignDate +
                ", totalSignCount=" + totalSignCount +
                ", LastSignCount=" + LastSignCount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastSignDate() {
        return LastSignDate;
    }

    public void setLastSignDate(Date lastSignDate) {
        LastSignDate = lastSignDate;
    }

    public Integer getTotalSignCount() {
        return totalSignCount;
    }

    public void setTotalSignCount(Integer totalSignCount) {
        this.totalSignCount = totalSignCount;
    }

    public Integer getLastSignCount() {
        return LastSignCount;
    }

    public void setLastSignCount(Integer lastSignCount) {
        LastSignCount = lastSignCount;
    }
}
