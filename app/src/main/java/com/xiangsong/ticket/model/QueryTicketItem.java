package com.xiangsong.ticket.model;

import java.io.Serializable;

/**
 * Created by xiangsong on 2016/9/17.
 */
public class QueryTicketItem implements Serializable{
    private String fromCity;
    private String toCity;
    private String date;
    private boolean isOnlyQueryG;
    private boolean isOnlyQueryZ;

    public QueryTicketItem() {
        super();
    }

    public QueryTicketItem(String fromCity, String toCity, String date, boolean isOnlyQueryG, boolean isOnlyQueryZ) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
        this.isOnlyQueryG = isOnlyQueryG;
        this.isOnlyQueryZ = isOnlyQueryZ;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOnlyQueryG() {
        return isOnlyQueryG;
    }

    public void setOnlyQueryG(boolean onlyQueryG) {
        isOnlyQueryG = onlyQueryG;
    }

    public boolean isOnlyQueryZ() {
        return isOnlyQueryZ;
    }

    public void setOnlyQueryZ(boolean onlyQueryZ) {
        isOnlyQueryZ = onlyQueryZ;
    }
}
