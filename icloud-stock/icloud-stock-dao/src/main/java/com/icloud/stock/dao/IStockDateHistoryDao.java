package com.icloud.stock.dao;

import java.util.Date;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDateHistory;

public interface IStockDateHistoryDao extends StockBaseDao<StockDateHistory> {

	Date getMaxUpdateTime(Integer id);

	void deleteByStockId(Integer id);

}
