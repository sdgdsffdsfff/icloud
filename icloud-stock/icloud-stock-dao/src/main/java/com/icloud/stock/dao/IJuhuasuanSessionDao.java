package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.JuhuasuanSession;

public interface IJuhuasuanSessionDao extends StockBaseDao<JuhuasuanSession> {
	public static final String ID = "id";
	public static final String JUHUASUANID = "juhuasuanId";
	public static final String USERID = "userId";
	public static final String CREATETIME = "createTime";
	public static final String LASTUPDATETIME = "lastupdateTime";
	public static final String SESSIONID = "sessionId";
	public static final String COUNT = "count";
	public static final String LASTREADIP = "lastreadIp";
}
