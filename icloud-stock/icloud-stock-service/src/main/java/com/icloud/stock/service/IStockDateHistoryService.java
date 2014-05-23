package com.icloud.stock.service;

import java.util.Date;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.front.stock.entity.StockDataHistoryUpdateCriteria;
import com.icloud.stock.model.StockDateHistory;

public interface IStockDateHistoryService extends
		ISqlBaseService<StockDateHistory> {

	Date getMaxUpdateTime(Integer id);

	StockDataHistoryUpdateCriteria getChinaStockDataHistoryTableStatus(
			Integer id);

	void deleteByStockId(Integer id);

}
