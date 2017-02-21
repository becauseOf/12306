package com.xiangsong.ticket.model;

/**
 * 用户时间
 * 
 * @author hezhujun
 *
 */
public class User {
	private int userid;
	private String email;
	private String name;
	private String sex;
	private String idcard;
	private String phone;
	private String lastLoginTime;
	private String state;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userid, String email, String name, String sex, String idcard, String phone, String lastLoginTime,
			String state) {
		super();
		this.userid = userid;
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.idcard = idcard;
		this.phone = phone;
		this.lastLoginTime = lastLoginTime;
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", name=" + name + ", sex=" + sex + ", idcard=" + idcard
				+ ", phone=" + phone + ", lastLoginTime=" + lastLoginTime + ", state=" + state + "]";
	}

}
