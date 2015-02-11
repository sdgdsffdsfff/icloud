package com.cinfo.shtb.cucumber.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.cinfo.shtb.cucumber.domain.AccountAggregate;
import com.cinfo.shtb.cucumber.service.IAccountService;

@Service("accountService")
public class AccountService implements IAccountService {
	private HashMap<Integer, AccountAggregate> userMap = new HashMap<Integer, AccountAggregate>();

	public boolean transfer(int fromUserId, int toUserId, int money) {
		AccountAggregate fromAccount = getAccountAggregate(fromUserId);
		AccountAggregate toAccount = getAccountAggregate(toUserId);
		return fromAccount.transfer(toAccount, money);
	}

	public void save(AccountAggregate accountAggregate) {
		this.userMap.put(accountAggregate.getUserId(), accountAggregate);
	}

	public AccountAggregate getAccountAggregate(int userId) {
		return userMap.get(userId);
	}
}
