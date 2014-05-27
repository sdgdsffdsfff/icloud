package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IStockDetailDao;
import com.icloud.stock.model.StockDetail;

@Repository("stockDetailDao")
public class StockDetailDaoImpl extends StockBaseDaoImpl<StockDetail> implements
		IStockDetailDao {
	public static final String STOCKCODE = "stockCode";
	public static final String STOCKID = "stockId";
}
