package com.icloud.front.stock.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.stock.model.CategoryStock;
import com.icloud.stock.model.Stock;
import com.icloud.stock.vo.StockVO;

@Service("stockListBussiness")
public class StockListBussiness extends BaseAction {
	public static final String CATEGORY_ID = "category.id";

	public Pagination<Stock> getStockListByType(int cateId, int pageNo,
			int limit) {
		Pagination<Stock> pagination = new Pagination<Stock>();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(limit);

		if (pageNo < 0)
			pageNo = 0;
		if (limit <= 0)
			limit = 40;
		int start = limit * pageNo;
		List<Stock> resultList = null;
		if (cateId != -1) {
			resultList = new ArrayList<Stock>();
			long count = this.categoryStockService.countByProperties(
					CATEGORY_ID, cateId);
			pagination.setTotalItemCount(count);
			List<CategoryStock> findAll = this.categoryStockService
					.findByProperties(CATEGORY_ID, cateId, start, limit);
			for (CategoryStock cs : findAll) {
				int userId = cs.getStock().getId();
				resultList.add(this.stockService.getById(userId));
				// resultList.add(StockVO.convert(cs.getStock()));
			}
			pagination.setData(resultList);
		} else {
			pagination.setTotalItemCount(this.stockService.count());
			List<Stock> list = this.stockService.findAll(start, limit);
			// resultList = StockVO.convert(list);
			pagination.setData(list);
		}
		pagination.build();
		return pagination;
	}

	public Pagination<Stock> getStockList(int pageNo, int limit) {
		return getStockListByType(-1, pageNo, limit);
	}

	public Pagination<Stock> getStockList(String cateId, String pageNo,
			int limit) {
		int id = -1;
		try {
			id = Integer.parseInt(cateId);
		} catch (Exception e) {
			id = -1;
		}
		int pn = 0;
		try {
			pn = Integer.parseInt(pageNo);
		} catch (Exception e) {
			pn = 0;
		}
		return getStockListByType(id, pn, limit);
	}

}
