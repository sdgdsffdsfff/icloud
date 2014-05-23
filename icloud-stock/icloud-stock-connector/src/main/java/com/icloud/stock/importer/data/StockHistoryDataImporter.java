package com.icloud.stock.importer.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.entity.StockDataHistoryUpdateCriteria;
import com.icloud.front.stock.entity.StockUpdateOperation.StockDataHistoryTableStatus;
import com.icloud.stock.connector.handler.impl.DownLoadCVSHandler;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.constant.StockConstants.StockLocation;

public class StockHistoryDataImporter extends BaseServiceImporter {
	public void saveAllHttpData(ArrayList<StockDateHistory> httpData) {
		if (ICloudUtils.isEmpty(httpData)) {
			LOGGER.info("httpData is null");
		}
		for (StockDateHistory stockDateHistory : httpData) {
			stockDateHistoryService.save(stockDateHistory);
		}
	}

	/**
	 * 进行爬取数据
	 *
	 * @param id
	 */
	public void processStockHistoryData(Stock stock) {
		StockDataHistoryUpdateCriteria stockDataHistoryUpdateCriteria = stockDateHistoryService
				.getChinaStockDataHistoryTableStatus(stock.getId());
		if (stockDataHistoryUpdateCriteria.getStatus() == StockDataHistoryTableStatus.NO) {
			/**
			 * 進行爬取
			 */
			LOGGER.info("fetch data {},{}", stock.getStockAllCode(),
					stock.getStockName());
			DownLoadCVSHandler handler = null;
			handler = new DownLoadCVSHandler(stock.getStockCode(),
					StockLocation.getStockLocation(stock.getStockLocation()),
					stock.getId());
			ArrayList<StockDateHistory> httpData = handler.getHttpData();
			saveAllHttpData(httpData);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (stockDataHistoryUpdateCriteria.getStatus() == StockDataHistoryTableStatus.UPDATE) {
			/**
			 * 進行更新
			 */
			LOGGER.info("update data {},{}", stock.getStockAllCode(),
					stock.getStockName());
			Date startDate = stockDataHistoryUpdateCriteria.getStartDate();
			Date endDate = stockDataHistoryUpdateCriteria.getEndDate();
			DownLoadCVSHandler handler = null;

			handler = new DownLoadCVSHandler(stock.getStockCode(),
					StockLocation.getStockLocation(stock.getStockLocation()),
					stock.getId(), DateUtils.getMonth(startDate),
					DateUtils.getDay(startDate), DateUtils.getYear(startDate),
					DateUtils.getMonth(endDate), DateUtils.getDay(endDate),
					DateUtils.getYear(endDate));
			ArrayList<StockDateHistory> httpData = handler.getHttpData();
			saveAllHttpData(httpData);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LOGGER.info("no update: {},{}", stock.getStockAllCode(),
					stock.getStockName());
		}
		// if (isNeedToUpdate) {
		// LOGGER.info("fetch data {},{}", stock.getStockAllCode(),
		// stock.getStockName());
		// /**
		// * 删除数据
		// */
		// // 删除过去的数据
		// this.stockDateHistoryService.deleteByStockId(stock.getId());
		//
		// } else {
		// LOGGER.info("no update: {},{}", stock.getStockAllCode(),
		// stock.getStockName());
		// }
	}

	public void loadData() {
		List<Stock> stockList = this.stockService.findAll();
		if (!ICloudUtils.isEmpty(stockList)) {
			int size = stockList.size();
			int count = 0;
			for (Stock stock : stockList) {
				processStockHistoryData(stock);
				count++;
				LOGGER.info("running, count={},size={}", count, size);
			}
		}
		LOGGER.info("ok!");
	}

	public static void main(String[] args) {
		StockHistoryDataImporter importer = new StockHistoryDataImporter();
		importer.loadData();
	}
}
