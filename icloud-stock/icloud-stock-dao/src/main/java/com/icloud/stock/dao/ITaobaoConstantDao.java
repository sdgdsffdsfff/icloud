package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.TaobaoConstant;

public interface ITaobaoConstantDao extends StockBaseDao<TaobaoConstant> {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String UPDATETIME = "updateTime";
	public static final String USERID = "userId";
	public static final String USERNAME = "userName";
}
