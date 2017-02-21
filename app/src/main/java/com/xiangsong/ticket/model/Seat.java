package com.xiangsong.ticket.model;

import java.io.Serializable;

/**
 * 席位信息
 * 
 * @author hezhujun
 *
 */
public class Seat implements Serializable{
	private int seatid;
	private String trainid;
	private int num;
	private String seatNubmer;
	private String stationStart;
	private String stationEnd;
	private String time;
	private String state;

	public int getSeatid() {
		return seatid;
	}

	public void setSeatid(int seatid) {
		this.seatid = seatid;
	}

	public String getTrainid() {
		return trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSeatNubmer() {
		return seatNubmer;
	}

	public void setSeatNubmer(String seatNubmer) {
		this.seatNubmer = seatNubmer;
	}

	public String getStationStart() {
		return stationStart;
	}

	public void setStationStart(String stationStart) {
		this.stationStart = stationStart;
	}

	public String getStationEnd() {
		return stationEnd;
	}

	public void setStationEnd(String stationEnd) {
		this.stationEnd = stationEnd;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Seat() {
		// TODO Auto-generated constructor stub
	}

	public Seat(int seatid, String trainid, int num, String seatNubmer, String stationStart, String stationEnd,
			String time, String state) {
		super();
		this.seatid = seatid;
		this.trainid = trainid;
		this.num = num;
		this.seatNubmer = seatNubmer;
		this.stationStart = stationStart;
		this.stationEnd = stationEnd;
		this.time = time;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Seat [seatid=" + seatid + ", trainid=" + trainid + ", num=" + num + ", seatNubmer=" + seatNubmer
				+ ", stationStart=" + stationStart + ", stationEnd=" + stationEnd + ", time=" + time + ", state="
				+ state + "]";
	}

}
