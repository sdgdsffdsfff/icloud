package com.icloud.front.stock.bussiness.detail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.bussiness.BaseAction;
import com.icloud.stock.model.CategoryStock;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.StockDetail;

@Service("stockDetailBussiness")
public class StockDetailBussiness extends BaseAction {

	public Stock getStockByStockCode(String stockCode) {
		Stock stock = this.stockService.getByStockCode(stockCode);
		return stock;
	}

	public StockDetail getStockDetailByStockCode(String stockCode) {
		return this.stockDetailService.getStockByStockCode(stockCode);
	}

	public Pagination<StockDateHistory> getStockDateHistoryList(
			Integer stockId, String pageNo, int limit) {
		if (!ICloudUtils.isNotNull(stockId))
			return null;
		int pn = 0;
		try {
			pn = Integer.parseInt(pageNo);
		} catch (Exception e) {
			pn = 0;
		}

		if (limit <= 0)
			limit = 40;

		Pagination<StockDateHistory> pagination = new Pagination<StockDateHistory>();
		pagination.setPageNo(pn);
		pagination.setPageSize(limit);

		int start = limit * pn;
		List<StockDateHistory> resultList = null;

		resultList = new ArrayList<StockDateHistory>();
		long count = this.stockDateHistoryService.countByStockId(stockId);
		pagination.setTotalItemCount(count);
		resultList = this.stockDateHistoryService.findByStockId(stockId, start,
				limit);
		pagination.setData(resultList);
		pagination.build();
		return pagination;
	}
}
