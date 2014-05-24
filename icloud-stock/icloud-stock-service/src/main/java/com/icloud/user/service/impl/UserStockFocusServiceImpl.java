package com.icloud.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.model.UserStockForcus;
import com.icloud.user.dao.IUserStockFocusDao;
import com.icloud.user.service.IUserAccessService;
import com.icloud.user.service.IUserStockFocusService;

@Service("userStockFocusService")
public class UserStockFocusServiceImpl extends SqlBaseService<UserStockForcus>
		implements IUserStockFocusService {
	@Resource(name = "userStockFocus")
	private IUserStockFocusDao userStockFocus;

	@Override
	protected IHibernateBaseDao<UserStockForcus> getDao() {
		// TODO Auto-generated method stub
		return userStockFocus;
	}

}
