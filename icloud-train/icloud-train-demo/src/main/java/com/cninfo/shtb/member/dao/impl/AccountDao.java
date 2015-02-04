/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IAccountDao;
import com.cninfo.shtb.mongo.entity.Account;

@Repository("shtb_AccountDao")
public class AccountDao extends ShbtMongoBaseDao<Account> implements
		IAccountDao {

}
