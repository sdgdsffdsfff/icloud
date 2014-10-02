package com.icloud.stock.analysis.strategy;

import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.StockDetail;

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
			System.out.println(stock.getStockCode() + "  "
					+ stock.getStockName());
			List<StockDateHistory> list2 = this.stockDateHistoryService
					.findByStockId(stock.getId());
			System.out.println("stock: " + list2.size());
			break;
		}
	}

	/**
	 * 抽取大笨像数据
	 */
	private void processBigBang(List<StockDateHistory> list) {
		if (ICloudUtils.isEmpty(list))
			return;
		System.out.println(list.size());
	}
}
