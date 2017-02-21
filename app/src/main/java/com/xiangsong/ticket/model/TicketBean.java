package com.xiangsong.ticket.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TicketBean implements Serializable{
	private String trainid;
	private String startStation;
	private String endStation;
	private String startTime;
	private String endTime;
	private String time;
	private int ruanwo;
	private BigDecimal ruanwoPrice;
	private int yingwo;
	private BigDecimal yingwoPrice;
	private int ruanzuo;
	private BigDecimal ruanzuoPrice;
	private int yingzuo;
	private BigDecimal yingzuoPrice;
	private String date;

	public String getTrainid() {
		return trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getRuanwo() {
		return ruanwo;
	}

	public void setRuanwo(int ruanwo) {
		this.ruanwo = ruanwo;
	}

	public int getYingwo() {
		return yingwo;
	}

	public void setYingwo(int yingwo) {
		this.yingwo = yingwo;
	}

	public int getRuanzuo() {
		return ruanzuo;
	}

	public void setRuanzuo(int ruanzuo) {
		this.ruanzuo = ruanzuo;
	}

	public int getYingzuo() {
		return yingzuo;
	}

	public void setYingzuo(int yingzuo) {
		this.yingzuo = yingzuo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getRuanwoPrice() {
		return ruanwoPrice;
	}

	public void setRuanwoPrice(BigDecimal ruanwoPrice) {
		this.ruanwoPrice = ruanwoPrice;
	}

	public BigDecimal getYingwoPrice() {
		return yingwoPrice;
	}

	public void setYingwoPrice(BigDecimal yingwoPrice) {
		this.yingwoPrice = yingwoPrice;
	}

	public BigDecimal getRuanzuoPrice() {
		return ruanzuoPrice;
	}

	public void setRuanzuoPrice(BigDecimal ruanzuoPrice) {
		this.ruanzuoPrice = ruanzuoPrice;
	}

	public BigDecimal getYingzuoPrice() {
		return yingzuoPrice;
	}

	public void setYingzuoPrice(BigDecimal yingzuoPrice) {
		this.yingzuoPrice = yingzuoPrice;
	}

	public TicketBean() {
		this.ruanwoPrice = new BigDecimal(0);
		this.yingwoPrice = new BigDecimal(0);
		this.ruanzuoPrice = new BigDecimal(0);
		this.yingzuoPrice = new BigDecimal(0);
	}

	public TicketBean(String trainid, String startStation, String endStation, String startTime, String endTime,
			String time, int ruanwo, BigDecimal ruanwoPrice, int yingwo, BigDecimal yingwoPrice, int ruanzuo,
			BigDecimal ruanzuoPrice, int yingzuo, BigDecimal yingzuoPrice, String date) {
		super();
		this.trainid = trainid;
		this.startStation = startStation;
		this.endStation = endStation;
		this.startTime = startTime;
		this.endTime = endTime;
		this.time = time;
		this.ruanwo = ruanwo;
		this.ruanwoPrice = ruanwoPrice;
		this.yingwo = yingwo;
		this.yingwoPrice = yingwoPrice;
		this.ruanzuo = ruanzuo;
		this.ruanzuoPrice = ruanzuoPrice;
		this.yingzuo = yingzuo;
		this.yingzuoPrice = yingzuoPrice;
		this.date = date;
	}

	@Override
	public String toString() {
		return "TicketBean [trainid=" + trainid + ", startStation=" + startStation + ", endStation=" + endStation
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", time=" + time + ", ruanwo=" + ruanwo
				+ ", ruanwoPrice=" + ruanwoPrice + ", yingwo=" + yingwo + ", yingwoPrice=" + yingwoPrice + ", ruanzuo="
				+ ruanzuo + ", ruanzuoPrice=" + ruanzuoPrice + ", yingzuo=" + yingzuo + ", yingzuoPrice=" + yingzuoPrice
				+ ", date=" + date + "]";
	}

}
