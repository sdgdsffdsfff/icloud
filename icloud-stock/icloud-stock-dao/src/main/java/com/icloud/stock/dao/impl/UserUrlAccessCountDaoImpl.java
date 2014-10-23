package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.model.UserUrlAccessCount;

@Repository("userUrlAccessCountDao")
public class UserUrlAccessCountDaoImpl extends
		StockBaseDaoImpl<UserUrlAccessCount> implements IUserUrlAccessCountDao {

}
