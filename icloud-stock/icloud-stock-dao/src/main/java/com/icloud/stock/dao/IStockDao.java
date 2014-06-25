package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.Stock;

public interface IStockDao extends StockBaseDao<Stock> {
	public static final String STOCKALLCODE = "stockAllCode";
	public static final String STOCKCODE = "stockCode";
}
