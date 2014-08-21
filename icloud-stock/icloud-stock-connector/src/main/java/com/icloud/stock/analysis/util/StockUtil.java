package com.icloud.stock.analysis.util;

import java.util.Date;
import java.util.List;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.search.util.DateUtil;
import com.icloud.stock.bean.StockPrice;
import com.icloud.stock.dict.StockConstants;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.StockDivinePrice;

public class StockUtil {
	public static void fillStock(Stock stock, Stock tmpStock) {
		if (ICloudUtils.isNotNull(stock) && ICloudUtils.isNotNull(tmpStock)) {
			stock.setCurrentPrice(tmpStock.getCurrentPrice());// 当前价
			stock.setChg(tmpStock.getChg());// 涨跌额
			stock.setPercent(tmpStock.getPercent());// 涨跌幅
			stock.setLastClose(tmpStock.getLastClose());// 上个交易闭价
			stock.setOpen(tmpStock.getOpen());// 上个交易日开价
			stock.setHigh(tmpStock.getHigh());// 当日最高价
			stock.setLow(tmpStock.getLow());// 当日最低价
			stock.setVolume(tmpStock.getVolume());// 成交量
			stock.setHigh52w(tmpStock.getHigh52w());// 52周最高
			stock.setLow52w(tmpStock.getLow52w());// 52周最低
			stock.setAmount(tmpStock.getAmount());// 成交额
			stock.setPreLow(tmpStock.getPreLow());// 预测最低
			stock.setPreHigh(tmpStock.getPreHigh());// 预测最高
			stock.setPrePercent(tmpStock.getPrePercent());// 波动比例
			stock.setPreCare(tmpStock.getPreCare());// 预测关注度
			stock.setUpdateTime(new Date());
			double totalMoney = stock.getTotalStock() * stock.getCurrentPrice();
			stock.setTotalMoney(totalMoney);
		}
	}

	public static void fillStock(Stock stock, double currentPrice, double chg,
			double percent, double lastClose, double open, double high,
			double low, double volume, double high52w, double low52w,
			double amount, double preLow, double preHigh, int prePercent,
			int preCare) {
		if (ICloudUtils.isNotNull(stock)) {
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
			tmpStock.setPreLow(preLow);// 预测最低
			tmpStock.setPreHigh(preHigh);// 预测最高
			tmpStock.setPrePercent(prePercent);// 波动比例
			tmpStock.setPreCare(preCare);// 预测关注度
			fillStock(stock, tmpStock);
		}
	}

	public static boolean fillEmptyStock(Stock stock) {
		if (ICloudUtils.isNotNull(stock)) {
			if (stock.getAmount() != -1) {
				fillStock(stock, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d,
						-1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1.0d, -1, -1);
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得近52周最极端价格
	 *
	 * @param list
	 * @return
	 */
	public static StockPrice get52wPrice(List<StockDateHistory> list) {
		if (!ICloudUtils.isEmpty(list)) {
			double maxPrice = -1.0d;
			double minPrice = 999999.0d;
			for (StockDateHistory history : list) {
				double price = history.getOpenPrice();
				if (price > maxPrice) {
					maxPrice = price;
				}
				if (price < minPrice) {
					minPrice = price;
				}
			}
			StockPrice price = new StockPrice();
			price.setHighPrice(maxPrice);
			price.setLowPrice(minPrice);
			return price;
		}
		return null;
	}

	public static StockDivinePrice getStockDivinePrice(Stock stock,
			Date nexStockDate) {
		if (ICloudUtils.isNotNull(stock)) {
			StockDivinePrice price = new StockDivinePrice();
			price.setStockId(stock.getId());
			price.setStockCode(stock.getStockCode());
			price.setPreLow(stock.getPreLow());
			price.setPreHigh(stock.getHigh());
			price.setPreDateTime(DateUtils.getDate(nexStockDate,
					StockConstants.STOCK_HISTORY_STRING));
			price.setCreateTime(new Date());
			price.setUserId(StockConstants.SYSTEM_USER_ID);
			price.setUserName(StockConstants.SYSTEM_USER_NAME);
			price.setUserComment("比较低级的预测哦");
			price.setPrePercent(stock.getPrePercent());
			price.setPreCare(stock.getPreCare());
			return price;
		}
		return null;
	}
}
