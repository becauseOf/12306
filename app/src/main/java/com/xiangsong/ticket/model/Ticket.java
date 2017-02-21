package com.xiangsong.ticket.model;

/**
 * 车票信息
 * 
 * @author hezhujun
 *
 */
public class Ticket {
	private int ticketid;
	private String trainid;
	private String date;
	private int trainBox;
	private String seatNum;
	private double price;
	private String startStation;
	private String endStation;
	private String name;
	private String pasgtype;
	private String idcard;
	private String sellType;
	private String sellDetail;
	private String sellTime;
	private String ticketState;
	private String stateChgPeople;
	private String stateChgStation;
	private String stateChgTime;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTicketid() {
		return ticketid;
	}

	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}

	public String getTrainid() {
		return trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTrainBox() {
		return trainBox;
	}

	public void setTrainBox(int trainBox) {
		this.trainBox = trainBox;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasgtype() {
		return pasgtype;
	}

	public void setPasgtype(String pasgtype) {
		this.pasgtype = pasgtype;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getSellDetail() {
		return sellDetail;
	}

	public void setSellDetail(String sellDetail) {
		this.sellDetail = sellDetail;
	}

	public String getSellTime() {
		return sellTime;
	}

	public void setSellTime(String sellTime) {
		this.sellTime = sellTime;
	}

	public String getTicketState() {
		return ticketState;
	}

	public void setTicketState(String ticketState) {
		this.ticketState = ticketState;
	}

	public String getStateChgPeople() {
		return stateChgPeople;
	}

	public void setStateChgPeople(String stateChgPeople) {
		this.stateChgPeople = stateChgPeople;
	}

	public String getStateChgStation() {
		return stateChgStation;
	}

	public void setStateChgStation(String stateChgStation) {
		this.stateChgStation = stateChgStation;
	}

	public String getStateChgTime() {
		return stateChgTime;
	}

	public void setStateChgTime(String stateChgTime) {
		this.stateChgTime = stateChgTime;
	}

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketid, String trainid, String date, int trainBox, String seatNum, String startStation,
			String endStation, String name, String pasgtype, String idcard, String sellType, String sellDetail,
			String sellTime, String ticketState, String stateChgPeople, String stateChgStation, String stateChgTime) {
		super();
		this.ticketid = ticketid;
		this.trainid = trainid;
		this.date = date;
		this.trainBox = trainBox;
		this.seatNum = seatNum;
		this.startStation = startStation;
		this.endStation = endStation;
		this.name = name;
		this.pasgtype = pasgtype;
		this.idcard = idcard;
		this.sellType = sellType;
		this.sellDetail = sellDetail;
		this.sellTime = sellTime;
		this.ticketState = ticketState;
		this.stateChgPeople = stateChgPeople;
		this.stateChgStation = stateChgStation;
		this.stateChgTime = stateChgTime;
	}

	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", trainid=" + trainid + ", date=" + date + ", trainBox=" + trainBox
				+ ", seatNum=" + seatNum + ", startStation=" + startStation + ", endStation=" + endStation + ", name="
				+ name + ", pasgtype=" + pasgtype + ", idcard=" + idcard + ", sellType=" + sellType + ", sellDetail="
				+ sellDetail + ", sellTime=" + sellTime + ", ticketState=" + ticketState + ", stateChgPeople="
				+ stateChgPeople + ", stateChgStation=" + stateChgStation + ", stateChgTime=" + stateChgTime + "]";
	}

}
