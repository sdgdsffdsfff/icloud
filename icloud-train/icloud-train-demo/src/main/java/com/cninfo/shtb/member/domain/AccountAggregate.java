package com.cninfo.shtb.member.domain;

import org.dozer.DozerBeanMapper;

import com.cninfo.shtb.member.dao.IAccountDao;
import com.cninfo.shtb.mongo.entity.Account;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.mongo.entity.BaseEntity;

public class AccountAggregate extends BaseEntity {
	private String userName;
	private int userId;
	private int money;

	private IAccountDao accountDao;

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

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	private void update() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		Account account = (Account) mapper.map(this, Account.class);
		// this.accountDao.updateAndAppend(account);
		Account account2 = this.accountDao.getByUserId(account.getUserId());
		account2.setMoney(account.getMoney());
		this.accountDao.updateAndAppend(account2);
	}

	public boolean transfer(AccountAggregate toAccountAggregate, int money) {
		if (!ICloudUtils.isNotNull(toAccountAggregate))
			return false;

		if (money <= 0)
			return false;
		this.money = this.money - money;
		this.update();

		toAccountAggregate.setMoney(money + toAccountAggregate.getMoney());
		toAccountAggregate.update();
		return true;
	}

}
