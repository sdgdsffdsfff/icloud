package com.icloud.front.user.pojo;

public class UserInfo {
	private String userName;
	private int userId;
	private String email;
	private boolean addUser;
	private boolean open;
	private boolean urlOper;
	private String taobaoUrl;
	private String taobaohosthref;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAddUser() {
		return addUser;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isUrlOper() {
		return urlOper;
	}

	public void setUrlOper(boolean urlOper) {
		this.urlOper = urlOper;
	}

	public String getTaobaoUrl() {
		return taobaoUrl;
	}

	public void setTaobaoUrl(String taobaoUrl) {
		this.taobaoUrl = taobaoUrl;
	}

	public String getTaobaohosthref() {
		return taobaohosthref;
	}

	public void setTaobaohosthref(String taobaohosthref) {
		this.taobaohosthref = taobaohosthref;
	}

}
