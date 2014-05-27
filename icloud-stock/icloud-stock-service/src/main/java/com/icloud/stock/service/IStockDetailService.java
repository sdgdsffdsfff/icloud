package com.icloud.stock.service;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDetail;

public interface IStockDetailService extends ISqlBaseService<StockDetail> {

	StockDetail getStockByStockCode(String stockCode);

	StockDetail getStockByStockId(Integer stockId);

}
