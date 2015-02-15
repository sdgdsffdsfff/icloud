package com.icloud.front.stock.pojo;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.StockDateHistory;

public class StockDateHistoryResult {
	private String stockName;
	private List<String> axisData;
	private List<List<String>> dateHistorys;
	private List<String> donePricesData;
	private List<String> amountdata;

	public static StockDateHistoryResult convertToStockDateHistoryResult(
			String stockName, List<StockDateHistory> list) {
		if (ICloudUtils.isEmpty(list))
			return null;
		StockDateHistoryResult result = new StockDateHistoryResult();
		result.setStockName(stockName);

		List<String> axisData = new ArrayList<String>();
		List<List<String>> dateHistorys = new ArrayList<List<String>>();
		List<String> donePricesData = new ArrayList<String>();
		List<String> amountdata = new ArrayList<String>();

		for (int i = list.size() - 1; i >= 0; i--) {

			StockDateHistory dateHistory = list.get(i);
			List<String> dateHistoryStr = getDateHistoryStr(dateHistory);
			String axisDataStr = DateUtils.formatDate(
					dateHistory.getCreateTime(), "yyyy/MM/dd");
			String donePriceStr = ICloudUtils.getDigitalString(dateHistory
					.getAdjPrice() * dateHistory.getVolume());
			String volumeStr = ICloudUtils.getDigitalString(dateHistory
					.getVolume());

			axisData.add(axisDataStr);
			dateHistorys.add(dateHistoryStr);
			donePricesData.add(donePriceStr);
			amountdata.add(volumeStr);
		}

		result.setAxisData(axisData);
		result.setDateHistorys(dateHistorys);
		result.setDonePricesData(donePricesData);
		result.setAmountdata(amountdata);
		return result;
	}

	// [ 2320.26, 2302.6, 2287.3, 2362.94 ],
	// 开盘，收盘，最低，最高
	public static List<String> getDateHistoryStr(StockDateHistory dateHistory) {
		List<String> list = new ArrayList<String>();
		list.add(ICloudUtils.getDigitalString(dateHistory.getOpenPrice()));
		list.add(ICloudUtils.getDigitalString(dateHistory.getClosePrice()));
		list.add(ICloudUtils.getDigitalString(dateHistory.getLowPrice()));
		list.add(ICloudUtils.getDigitalString(dateHistory.getHighPrice()));
		return list;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<String> getAxisData() {
		return axisData;
	}

	public void setAxisData(List<String> axisData) {
		this.axisData = axisData;
	}

	public List<String> getDonePricesData() {
		return donePricesData;
	}

	public void setDonePricesData(List<String> donePricesData) {
		this.donePricesData = donePricesData;
	}

	public List<String> getAmountdata() {
		return amountdata;
	}

	public void setAmountdata(List<String> amountdata) {
		this.amountdata = amountdata;
	}

	public List<List<String>> getDateHistorys() {
		return dateHistorys;
	}

	public void setDateHistorys(List<List<String>> dateHistorys) {
		this.dateHistorys = dateHistorys;
	}

}
