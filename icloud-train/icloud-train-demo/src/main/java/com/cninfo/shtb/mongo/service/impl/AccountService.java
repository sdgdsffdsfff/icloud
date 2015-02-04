package com.cninfo.shtb.mongo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cninfo.shtb.member.dao.IAccountDao;
import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.mongo.entity.Account;
import com.cninfo.shtb.mongo.service.IAccountService;
import com.info.shtb.member.factory.IAccountFactory;

@Service("shtb_AccountService")
public class AccountService implements IAccountService {
	@Resource(name = "shtb_AccountDao")
	private IAccountDao accountDao;

	@Resource(name = "shtb_AccountFactory")
	private IAccountFactory accountFactory;

	public boolean deleteAll() {
		return this.accountDao.deleteAll();
	}

	public boolean saveList(List<Account> accounts) {
		this.accountDao.saveEntity(accounts);
		return true;
	}

	public boolean transfer(int fromUserId, int toUserId, int money) {
		AccountAggregate fromAccountAggregate = this.accountFactory
				.getAccountAggregate(fromUserId);
		AccountAggregate toAccountAggregate = this.accountFactory
				.getAccountAggregate(toUserId);

		return fromAccountAggregate.transfer(toAccountAggregate, money);
	}

	@Override
	public AccountAggregate getAccount(int id) {
		return this.accountFactory
				.getAccountAggregate(id);
	}

}
