package com.cinfo.shtb.cucumber.domain;

import com.cninfo.shtb.member.domain.AggregateRoot;
import com.icloud.framework.util.ICloudUtils;

public class AccountAggregate extends AggregateRoot {
	private int userId; //用户Id
	private String userName; //用户名
	private int money;  //余额

	/**
	 * 转账服务，给指定的帐户转钱
	 */
	public boolean transfer(AccountAggregate toAccountAggregate, int money) {
		if (!ICloudUtils.isNotNull(toAccountAggregate))
			return false;

		if (money <= 0)
			return false;
		this.money = this.money - money; // 进行转账

		toAccountAggregate.setMoney(money + toAccountAggregate.getMoney()); //
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
