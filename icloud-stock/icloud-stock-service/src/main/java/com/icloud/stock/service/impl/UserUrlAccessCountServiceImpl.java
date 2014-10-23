package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.stock.service.IUserUrlAccessCountService;

@Service("userUrlAccessCountService")
public class UserUrlAccessCountServiceImpl extends
		SqlBaseService<UserUrlAccessCount> implements
		IUserUrlAccessCountService {

	@Resource(name = "userUrlAccessCountDao")
	private IUserUrlAccessCountDao userUrlAccessCountDao;

	@Override
	protected IHibernateBaseDao<UserUrlAccessCount> getDao() {
		// TODO Auto-generated method stub
		return userUrlAccessCountDao;
	}

}
