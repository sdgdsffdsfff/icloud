package com.icloud.front.stock.bussiness.detail;

import org.springframework.stereotype.Service;

import com.icloud.front.stock.bussiness.BaseAction;
import com.icloud.stock.model.Stock;

@Service("stockDetailBussiness")
public class StockDetailBussiness extends BaseAction {

	public Stock getStockByStockCode(String stockCode) {
		return this.stockService.getByStockCode(stockCode);
	}

}
