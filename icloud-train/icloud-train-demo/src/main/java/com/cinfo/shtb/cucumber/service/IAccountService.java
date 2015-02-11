package com.cinfo.shtb.cucumber.service;

import com.cinfo.shtb.cucumber.domain.AccountAggregate;

public interface IAccountService {
	// 存储
	public void save(AccountAggregate accountAggregate);
	// 读取
	public AccountAggregate getAccountAggregate(int userId);
    // 转账
	public boolean transfer(int fromUserId, int toUserId, int money);

}
