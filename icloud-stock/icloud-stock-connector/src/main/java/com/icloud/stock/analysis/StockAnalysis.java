package com.icloud.stock.analysis;

import java.util.List;

import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;

public class StockAnalysis extends BaseServiceImporter {

	public void ok(){
//		private Double currentPrice; //当前价
//		private Double chg; //涨跌额
//		private Double percent; //涨跌幅
//		private Double lastClose;//上个交易闭价
//		private Double open; //上个交易日开价
//		private Double high; //当日最高价
//		private Double low; //当日最低价
//		private Double volume; //成交量
//		private Double high52w; //52周最高
//		private Double low52w; //52周最低
//		private Double amount; //成交额
//		private Double preLow; //预测最低
//		private Double preHigh; //预测最高
//		private Integer prePercent; //波动比例
//		private Integer preCare; //预测关注度
	}
	public void process(Stock stock) {
		int age = this.stockDateHistoryService.countByStockId(stock.getId());
		if (age <= 0) {
			return;
		}

		List<StockDateHistory> list = this.stockDateHistoryService
				.findByStockId(stock.getId());
		for (StockDateHistory history : list) {
			System.out.println(history.getStockCode());
		}
		// this.stockService.update(stock);
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
