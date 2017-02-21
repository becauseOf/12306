package com.xiangsong.ticket.model;

/**
 * Created by gala on 2016/7/4.
 */
public class ProfileItem {
    private int imgId;
    private String text;

    public ProfileItem(int imgId, String text) {
        this.imgId = imgId;
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
