package com.icloud.front.user.pojo;

import com.icloud.user.dict.UserConstants;

public class UserInfo {
	private String userName;
	private int userId;
	private String email;
	private boolean addUser;
	private boolean open;
	private boolean urlOper;
	private int level;

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isSuper() {
		if (this.level == UserConstants.SUPER_USER)
			return true;
		return false;
	}

	public boolean isProxy() {
		if (this.level == UserConstants.SUPER_USER
				|| this.level == UserConstants.POXY_USER) {
			return true;
		}
		return false;
	}

}
