package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDetail;

public interface IStockDetailDao extends StockBaseDao<StockDetail> {
	public static final String ID = "id";
	public static final String STOCKID = "stockId";
	public static final String STOCKCODE = "stockCode";
	public static final String STOCKNAME = "stockName";
	public static final String COMPANYINFOWORK = "companyInfoWork";
	public static final String DETAILCONTENT = "detailContent";
	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";
	
}
