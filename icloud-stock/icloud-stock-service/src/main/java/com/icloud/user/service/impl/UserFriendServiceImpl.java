package com.icloud.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.model.UserFriend;
import com.icloud.user.dao.IUserFriendDao;
import com.icloud.user.service.IUserFriendService;

@Service("userFriendService")
public class UserFriendServiceImpl extends SqlBaseService<UserFriend> implements
		IUserFriendService {
	@Resource(name = "userFriendDao")
	private IUserFriendDao userFriendDao;

	@Override
	protected IHibernateBaseDao<UserFriend> getDao() {
		// TODO Auto-generated method stub
		return userFriendDao;
	}

}
