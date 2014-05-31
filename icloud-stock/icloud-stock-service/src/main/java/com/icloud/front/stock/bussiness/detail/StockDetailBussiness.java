package com.icloud.front.stock.bussiness.detail;

import org.springframework.stereotype.Service;

import com.icloud.front.stock.bussiness.BaseAction;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDetail;

@Service("stockDetailBussiness")
public class StockDetailBussiness extends BaseAction {

	public Stock getStockByStockCode(String stockCode) {
		return this.stockService.getByStockCode(stockCode);
	}

	public StockDetail getStockDetailByStockCode(String stockCode) {
		return this.stockDetailService.getStockByStockCode(stockCode);
	}
}
