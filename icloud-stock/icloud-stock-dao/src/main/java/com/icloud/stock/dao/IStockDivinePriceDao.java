package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDivinePrice;

public interface IStockDivinePriceDao extends StockBaseDao<StockDivinePrice> {
	public static final String ID = "id";
	public static final String STOCKID = "stockId";
	public static final String STOCKCODE = "stockCode";
	public static final String PRELOW = "preLow";
	public static final String PREHIGH = "preHigh";
	public static final String RESULTLOW = "resultLow";
	public static final String RESULTHIGH = "resultHigh";
	public static final String PREDATETIME = "preDateTime";
	public static final String CREATETIME = "createTime";
	public static final String USERID = "userId";
	public static final String USERNAME = "userName";
	public static final String USERCOMMENT = "userComment";
	public static final String PREPERCENT = "prePercent";
	public static final String PRECARE = "preCare";
}
