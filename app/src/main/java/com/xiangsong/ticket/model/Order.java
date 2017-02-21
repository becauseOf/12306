package com.xiangsong.ticket.model;

/**
 * Created by xiangsong on 2016/9/12.
 */
public class Order {
    //居然没有orderid//////////////////////////////////////////////
    private String trainNum;
    private String date;
    private String fromStation;
    private String toStation;
    private String price;
    private String name;
    private String state;

    public Order() {
    }

    public Order(String trainNum, String date, String fromStation, String toStation, String price, String name, String state) {
        this.trainNum = trainNum;
        this.date = date;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.price = price;
        this.name = name;
        this.state = state;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
