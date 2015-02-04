package com.info.shtb.member.factory.impl;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.cninfo.shtb.member.dao.IAccountDao;
import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.mongo.entity.Account;
import com.icloud.framework.mongo.service.impl.MongoBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.mongo.dao.IBasicDao;
import com.info.shtb.member.factory.IAccountFactory;

@Service("shtb_AccountFactory")
public class AccountFactory extends MongoBaseService<Account, ObjectId>
		implements IAccountFactory {
	@Resource(name = "shtb_AccountDao")
	private IAccountDao accountDao;

	@Override
	protected IBasicDao<Account, ObjectId> getDao() {
		// TODO Auto-generated method stub
		return this.accountDao;
	}

	@Override
	public AccountAggregate getAccountAggregate(int usrId) {
		Account account = this.accountDao.getByUserId(usrId);
		if (!ICloudUtils.isNotNull(account))
			return null;
		DozerBeanMapper mapper = new DozerBeanMapper();
		AccountAggregate accountAggregate = (AccountAggregate) mapper.map(
				account, AccountAggregate.class);
		accountAggregate.setAccountDao(accountDao);
		return accountAggregate;
	}
}
