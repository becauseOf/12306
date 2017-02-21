package com.xiangsong.ticket.model;

/**
 * Created by xiangsong on 2016/9/19.
 */
public class QuerySeat {
    private String from;
    private String to;
    private String trainid;
    private String seatClass;
    private String day;

    public QuerySeat() {
    }

    public QuerySeat(String from, String to, String trainid, String seatClass, String day) {
        this.from = from;
        this.to = to;
        this.trainid = trainid;
        this.seatClass = seatClass;
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTrainid() {
        return trainid;
    }

    public void setTrainid(String trainid) {
        this.trainid = trainid;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
