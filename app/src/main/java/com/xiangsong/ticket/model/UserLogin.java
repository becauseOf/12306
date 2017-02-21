package com.xiangsong.ticket.model;

/**
 * 用户登录信息
 * 
 * @author hezhujun
 *
 */
public class UserLogin {
	private int userid;
	private String email;
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLogin() {
		// TODO Auto-generated constructor stub
	}

	public UserLogin(int userid, String email, String password) {
		super();
		this.userid = userid;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLogin [userid=" + userid + ", email=" + email + ", password=" + password + "]";
	}

}
