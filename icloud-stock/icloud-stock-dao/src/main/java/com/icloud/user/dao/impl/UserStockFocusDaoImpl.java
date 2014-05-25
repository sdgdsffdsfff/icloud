package com.icloud.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.model.UserStockFocus;
import com.icloud.user.dao.IUserStockFocusDao;

@Repository("userStockFocusDao")
public class UserStockFocusDaoImpl extends StockBaseDaoImpl<UserStockFocus>
		implements IUserStockFocusDao {

}
