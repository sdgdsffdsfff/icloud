package com.cninfo.shtb.mongo.service;

import java.util.List;

import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.mongo.entity.Account;

public interface IAccountService {

	public boolean deleteAll();

	public boolean saveList(List<Account> accounts);

	public boolean transfer(int fromUserId, int toUserId, int money);

	public AccountAggregate getAccount(int fromId);

}
