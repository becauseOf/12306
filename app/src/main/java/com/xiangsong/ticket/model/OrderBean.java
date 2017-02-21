package com.xiangsong.ticket.model;

/**
 * 订单信息
 * 
 * @author hezhujun
 *
 */
public class OrderBean {
	private long orderid;
	private int userid;
	private String orderState;
	private String orderTime;

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public OrderBean() {
		// TODO Auto-generated constructor stub
	}

	public OrderBean(long orderid, int userid, String orderState, String orderTime) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.orderState = orderState;
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", userid=" + userid + ", orderState=" + orderState + ", orderTime="
				+ orderTime + "]";
	}

}
