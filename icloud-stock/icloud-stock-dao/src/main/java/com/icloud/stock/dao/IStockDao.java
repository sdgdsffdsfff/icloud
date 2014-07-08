package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.Stock;

public interface IStockDao extends StockBaseDao<Stock> {
	public static final String ID = "id";
	public static final String STOCKNAME = "stockName";
	public static final String STOCKCODE = "stockCode";
	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";
	public static final String STOCKLOCATION = "stockLocation";
	public static final String DESCRITION = "descrition";
	public static final String STOCKALLCODE = "stockAllCode";
	public static final String CURRENTPRICE = "currentPrice";
	public static final String CHG = "chg";
	public static final String PERCENT = "percent";
	public static final String LASTCLOSE = "lastClose";
	public static final String OPEN = "open";
	public static final String HIGH = "high";
	public static final String LOW = "low";
	public static final String VOLUME = "volume";
	public static final String HIGH52W = "high52w";
	public static final String LOW52W = "low52w";
	public static final String AMOUNT = "amount";
	public static final String PRELOW = "preLow";
	public static final String PREHIGH = "preHigh";
	public static final String PREPERCENT = "prePercent";
	public static final String PRECARE = "preCare";
	public static final String FLOWSTOCK = "flowStock";
	public static final String TOTALSTOCK = "totalStock";
	public static final String TOTALMONEY = "totalMoney";
	public static final String CATEGORYSTOCKS = "categoryStocks";
}
