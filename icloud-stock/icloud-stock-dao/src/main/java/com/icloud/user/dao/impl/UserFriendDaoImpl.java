package com.icloud.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.model.UserFriend;
import com.icloud.user.dao.IUserFriendDao;

@Repository("userFriendDao")
public class UserFriendDaoImpl extends StockBaseDaoImpl<UserFriend> implements
		IUserFriendDao {

}
