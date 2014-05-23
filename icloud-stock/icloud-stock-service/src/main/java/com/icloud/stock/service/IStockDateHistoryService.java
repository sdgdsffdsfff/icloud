package com.icloud.stock.service;

import java.util.Date;
import java.util.List;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.front.stock.entity.StockDataHistoryUpdateCriteria;
import com.icloud.stock.model.StockDateHistory;

public interface IStockDateHistoryService extends
		ISqlBaseService<StockDateHistory> {

	Date getMaxUpdateTime(Integer id);

	StockDataHistoryUpdateCriteria getChinaStockDataHistoryTableStatus(
			Integer id);

	void deleteByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id, int start, int limit);

	int countByStockId(Integer id);

}
