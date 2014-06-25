package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDetail;

public interface IStockDetailDao extends StockBaseDao<StockDetail> {
	public static final String STOCKCODE = "stockCode";
	public static final String STOCKID = "stockId";
}
