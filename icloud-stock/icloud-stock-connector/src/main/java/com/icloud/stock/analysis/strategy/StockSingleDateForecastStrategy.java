package com.icloud.stock.analysis.strategy;

import java.util.List;

import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年8月21日 下午7:25:20
 */
public class StockSingleDateForecastStrategy extends BaseServiceImporter {
	public static void main(String[] args) {
		StockSingleDateForecastStrategy stockSingleDateForecastStrategy = new StockSingleDateForecastStrategy();
		stockSingleDateForecastStrategy.singleDateForecast();
	}

	/**
	 * 
	 * void
	 * 
	 * @throws
	 */
	private void singleDateForecast() {
		List<Stock> list = this.stockService.findAll();
		for (Stock stock : list) {
			
//			System.out.println(stock.getStockCode() + "  "
//					+ stock.getStockName());
		}
	}
}
