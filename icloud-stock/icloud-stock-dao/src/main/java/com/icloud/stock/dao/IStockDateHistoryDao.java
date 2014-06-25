package com.icloud.stock.dao;

import java.util.Date;
import java.util.List;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDateHistory;

public interface IStockDateHistoryDao extends StockBaseDao<StockDateHistory> {
	public static final String STOCK_ID = "stockId";
	public static final String CREATE_TIME = "createTime";

	Date getMaxUpdateTime(Integer id);

	void deleteByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id, int start, int limit);

}
