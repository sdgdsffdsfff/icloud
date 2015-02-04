package com.info.shtb.member.factory;

import org.bson.types.ObjectId;

import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.mongo.entity.Account;
import com.icloud.framework.mongo.service.IMongoBaseService;

public interface IAccountFactory extends IMongoBaseService<Account, ObjectId> {
	public AccountAggregate getAccountAggregate(int usrId);
}