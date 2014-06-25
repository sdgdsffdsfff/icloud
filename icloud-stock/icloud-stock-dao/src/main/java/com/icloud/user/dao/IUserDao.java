package com.icloud.user.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.User;

public interface IUserDao extends StockBaseDao<User> {
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "userPassword";
	public static final String USERMAIL = "userEmail";
	public static final String USERSEX = "userSex";
	public static final String USERTEL = "userTel";
}
