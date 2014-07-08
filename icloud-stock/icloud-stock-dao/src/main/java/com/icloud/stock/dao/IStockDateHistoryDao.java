package com.icloud.stock.dao;

import java.util.Date;
import java.util.List;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.StockDateHistory;

public interface IStockDateHistoryDao extends StockBaseDao<StockDateHistory> {
	public static final String ID = "id";
	public static final String STOCKCODE = "stockCode";
	public static final String STOCKID = "stockId";
	public static final String CREATETIME = "createTime";
	public static final String OPENPRICE = "openPrice";
	public static final String HIGHPRICE = "highPrice";
	public static final String LOWPRICE = "lowPrice";
	public static final String CLOSEPRICE = "closePrice";
	public static final String VOLUME = "volume";
	public static final String ADJPRICE = "adjPrice";

	Date getMaxUpdateTime(Integer id);

	void deleteByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id);

	List<StockDateHistory> findByStockId(Integer id, int start, int limit);

}
