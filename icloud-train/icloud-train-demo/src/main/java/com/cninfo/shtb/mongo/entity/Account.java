package com.cninfo.shtb.mongo.entity;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.icloud.mongo.entity.BaseEntity;

@Entity("shtb.Account")
public class Account extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Indexed
	private String userName;
	@Indexed
	private int userId;
	private int money;

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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
