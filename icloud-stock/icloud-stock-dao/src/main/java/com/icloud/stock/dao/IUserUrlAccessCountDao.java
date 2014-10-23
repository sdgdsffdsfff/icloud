package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.UserUrlAccessCount;

public interface IUserUrlAccessCountDao extends StockBaseDao<UserUrlAccessCount> {
	public static final String ID = "id";
	public static final String USERID = "userId";
	public static final String COUNT = "count";
	public static final String ALLCOUNT = "allCount";
	public static final String CREATETIME = "createTime";
}
