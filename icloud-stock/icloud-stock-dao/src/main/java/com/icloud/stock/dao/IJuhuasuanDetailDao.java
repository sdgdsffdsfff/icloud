package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.JuhuasuanDetail;

public interface IJuhuasuanDetailDao extends StockBaseDao<JuhuasuanDetail> {
	public static final String ID = "id";
	public static final String PERFER = "perfer";
	public static final String PERFERHOST = "perferHost";
	public static final String PERFERIP = "perferIp";
	public static final String URLID = "urlId";
	public static final String CREATETIME = "createTime";
	public static final String OTHERPARAM = "otherParam";
	public static final String USERID = "userId";
}
