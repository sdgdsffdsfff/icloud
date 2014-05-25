package com.icloud.stock.analysis;

import java.util.Date;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.analysis.util.StockUtil;
import com.icloud.stock.bean.StockPrice;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.StockDivinePrice;
import com.icloud.stock.util.ChinaStockUtil;

public class StockAnalysis extends BaseServiceImporter {
	public Stock getBaseStockInfo(List<StockDateHistory> list) {
		if (ICloudUtils.isEmpty(list))
			return null;
		/**
		 * 计算标准数据
		 */
		StockDateHistory currentDateHistory = list.get(0);
		StockDateHistory lastDateHistory = list.get(1);

		double currentPrice = currentDateHistory.getClosePrice();
		double chg = currentDateHistory.getClosePrice()
				- currentDateHistory.getOpenPrice();
		double percent = (currentDateHistory.getOpenPrice() > 0) ? (currentDateHistory
				.getClosePrice() - currentDateHistory.getOpenPrice())
				/ currentDateHistory.getOpenPrice() : -1.0d;
		double lastClose = lastDateHistory.getClosePrice();
		double open = currentDateHistory.getOpenPrice();
		double high = currentDateHistory.getHighPrice();
		double low = currentDateHistory.getLowPrice();
		double high52w = -1.0d;
		double low52w = -1.0d;

		StockPrice wPrice = StockUtil.get52wPrice(list);
		if (ICloudUtils.isNotNull(wPrice)) {
			high52w = wPrice.getHighPrice();
			low52w = wPrice.getLowPrice();
		}
		double volume = currentDateHistory.getVolume();
		double amount = currentDateHistory.getVolume()
				* currentDateHistory.getAdjPrice();

		Stock tmpStock = new Stock();
		tmpStock.setCurrentPrice(currentPrice);// 当前价
		tmpStock.setChg(chg);// 涨跌额
		tmpStock.setPercent(percent);// 涨跌幅
		tmpStock.setLastClose(lastClose);// 上个交易闭价
		tmpStock.setOpen(open);// 上个交易日开价
		tmpStock.setHigh(high);// 当日最高价
		tmpStock.setLow(low);// 当日最低价
		tmpStock.setVolume(volume);// 成交量
		tmpStock.setHigh52w(high52w);// 52周最高
		tmpStock.setLow52w(low52w);// 52周最低
		tmpStock.setAmount(amount);// 成交额
		return tmpStock;
	}

	public void divineStock(List<StockDateHistory> list, Stock baseStockInfo) {
		if (!ICloudUtils.isEmpty(list) && ICloudUtils.isNotNull(baseStockInfo)) {
			double volume = baseStockInfo.getVolume();
			double amount = baseStockInfo.getAmount();

			double currentPrice = baseStockInfo.getCurrentPrice();
			double chg = baseStockInfo.getChg();

			double price1 = currentPrice + currentPrice * chg * 0.05;
			double price2 = currentPrice + currentPrice * chg * 0.25;

			double preLow = price1 > price2 ? price2 : price1;
			double preHigh = price1 > price2 ? price1 : price2;
			int prePercent = (int) Math.ceil(chg * amount);
			int preCare = (int) Math.ceil(chg * amount);

			// tmpStock.setPreLow(preLow);// 预测最低
			// tmpStock.setPreHigh(preHigh);// 预测最高
			// tmpStock.setPrePercent(prePercent);// 波动比例
			// tmpStock.setPreCare(preCare);// 预测关注度
			baseStockInfo.setPreLow(preLow);
			baseStockInfo.setPreHigh(preHigh);
			baseStockInfo.setPrePercent(prePercent);
			baseStockInfo.setPreCare(preCare);
		}
	}

	/**
	 * 1)首先计算一些标准数据 2)对数据进行预测 3)保存数据
	 *
	 * @param stock
	 */
	public void process(Stock stock) {
		int age = this.stockDateHistoryService.countByStockId(stock.getId());
		if (age <= 0) {// 如果沒有数据,则进行处理
			if (StockUtil.fillEmptyStock(stock)) {
				this.stockService.update(stock);
			}
			return;
		}
		List<StockDateHistory> list = this.stockDateHistoryService
				.findByStockId(stock.getId(), 0, 5 * 52);

		StockDateHistory currentDateHistory = list.get(0);
		Date nexStockDate = ChinaStockUtil.getNexStockDate(currentDateHistory
				.getCreateTime());

		Stock baseStockInfo = getBaseStockInfo(list);
		/**
		 * 进行预测
		 */
		divineStock(list, baseStockInfo);
		/**
		 * 填充数据
		 */
		StockUtil.fillStock(stock, baseStockInfo);
		this.stockService.update(stock);
		/**
		 * 预测函数
		 */
		StockDivinePrice stockDivinePrice = StockUtil.getStockDivinePrice(
				stock, nexStockDate);
		stockDivinePriceService.save(stockDivinePrice);
	}

	/**
	 * 基于单一股票分析
	 */
	public void singleAnalysis() {
		List<Stock> stockList = this.stockService.findAll();
		int count = 0;
		int sum = stockList.size();
		for (Stock stock : stockList) {
			count++;
			process(stock);
			LOGGER.info("singleAnalysis running, count = {},sum = {}", count,
					sum);
		}
		LOGGER.info("singleAnalysis ok");
	}
}
