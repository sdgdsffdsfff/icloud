package com.icloud.front.user.pojo;

public class RegisterUser extends LoginUser {
	private String username;
	private String confirm_password;
	private String qq;
	private String telphone;
	private String readContract;
	private String usersex;
	private String token;
	private String chinaName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getReadContract() {
		return readContract;
	}

	public void setReadContract(String readContract) {
		this.readContract = readContract;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	@Override
	public String toString() {
		return "RegisterUser [username=" + username + ", email=" + getEmail()
				+ ", password=" + getPassword() + ", confirm_password="
				+ confirm_password + ", qq=" + qq + ", telphone=" + telphone
				+ ", readContract=" + readContract + ", usersex=" + usersex
				+ "]";
	}

}
