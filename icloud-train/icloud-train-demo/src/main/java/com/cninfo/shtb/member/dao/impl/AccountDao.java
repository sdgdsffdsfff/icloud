/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IAccountDao;
import com.cninfo.shtb.mongo.entity.Account;
import com.icloud.framework.util.ICloudUtils;

@Repository("shtb_AccountDao")
public class AccountDao extends ShbtMongoBaseDao<Account> implements
		IAccountDao {

	@Override
	public Account getByUserId(int usrId) {
		List<Account> list = this.findByProperty("userId", usrId);
		if (ICloudUtils.isEmpty(list)) {
			return null;
		}
		Account account = list.get(0);
		return account;
	}

}
