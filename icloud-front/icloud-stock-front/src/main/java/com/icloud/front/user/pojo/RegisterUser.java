package com.icloud.front.user.pojo;

public class RegisterUser {
	private String username;
	private String email;
	private String password;
	private String confirm_password;
	private String qq;
	private String telphone;
	private String readContract;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "RegisterUser [username=" + username + ", email=" + email
				+ ", password=" + password + ", confirm_password="
				+ confirm_password + ", qq=" + qq + ", telphone=" + telphone
				+ ", readContract=" + readContract + "]";
	}

}
