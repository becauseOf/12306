package com.xiangsong.ticket.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiangsong on 2016/9/14.
 */
public class RemainTicket implements Serializable{
    private String trainId;
    private String startTime;
    private String endTime;
    private String spanTime;
    private int price;
    private List<HashMap<String,Integer>> remainNums;

    public RemainTicket() {
        super();
    }

    public RemainTicket(String trainId, String startTime, String endTime, String spanTime, int price, List<HashMap<String, Integer>> remainNums) {
        this.trainId = trainId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spanTime = spanTime;
        this.price = price;
        this.remainNums = remainNums;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSpanTime() {
        return spanTime;
    }

    public void setSpanTime(String spanTime) {
        this.spanTime = spanTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<HashMap<String, Integer>> getRemainNums() {
        return remainNums;
    }

    public void setRemainNums(List<HashMap<String, Integer>> remainNums) {
        this.remainNums = remainNums;
    }
}
