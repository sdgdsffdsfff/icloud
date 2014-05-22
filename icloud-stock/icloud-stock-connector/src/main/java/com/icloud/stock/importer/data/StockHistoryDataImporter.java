package com.icloud.stock.importer.data;

import java.util.ArrayList;
import java.util.List;

import com.icloud.stock.connector.handler.impl.DownLoadCVSHandler;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.constant.StockConstants.StockLocation;

public class StockHistoryDataImporter extends BaseServiceImporter {
	/**
	 * 进行爬取数据
	 *
	 * @param id
	 */
	public void processStockHistoryData(Stock stock) {
		boolean isNeedToUpdate = stockDateHistoryService
				.isUpdateNowInChinaStock(stock.getId());
		if (isNeedToUpdate) {
			LOGGER.info("fetch data {},{}", stock.getStockAllCode(),
					stock.getStockName());
			/**
			 * 删除数据
			 */
			// 删除过去的数据
			this.stockDateHistoryService.deleteByStockId(stock.getId());
			/**
			 * 進行爬取
			 */

			DownLoadCVSHandler handler = null;
			handler = new DownLoadCVSHandler(stock.getStockCode(),
					StockLocation.getStockLocation(stock.getStockLocation()),
					stock.getId());
			ArrayList<StockDateHistory> httpData = handler.getHttpData();
			for (StockDateHistory stockDateHistory : httpData) {
				stockDateHistoryService.save(stockDateHistory);
			}
		}else{
			LOGGER.info("no update: {},{}", stock.getStockAllCode(),
					stock.getStockName());
		}
	}

	public void loadData() {
		List<Stock> stockList = this.stockService.findAll();
		for (Stock stock : stockList) {
			processStockHistoryData(stock);
//			break;
		}
		LOGGER.info("ok!");
	}

	public static void main(String[] args) {
		StockHistoryDataImporter importer = new StockHistoryDataImporter();
		importer.loadData();

	}
}
