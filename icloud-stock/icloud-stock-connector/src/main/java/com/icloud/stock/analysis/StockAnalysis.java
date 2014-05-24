package com.icloud.stock.analysis;

import java.util.List;

import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;

public class StockAnalysis extends BaseServiceImporter {

	public void process(Stock stock) {
		int age = this.stockDateHistoryService.countByStockId(stock.getId());
		if (age <= 0) {
			return;
		}
		List<StockDateHistory> list = this.stockDateHistoryService
				.findByStockId(stock.getId(), 0, 100);

//		this.stockService.update(stock);
	}

	/**
	 * 基于单一股票分析
	 */
	public void singleAnalysis() {
		List<Stock> stockList = this.stockService.findAll();
		for (Stock stock : stockList) {
			process(stock);
			break;
		}
	}

	public static void main(String[] args) {
		StockAnalysis stockAnalysis = new StockAnalysis();
		stockAnalysis.singleAnalysis();
	}
}
