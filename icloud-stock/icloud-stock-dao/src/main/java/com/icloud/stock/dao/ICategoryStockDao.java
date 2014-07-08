package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.CategoryStock;

public interface ICategoryStockDao extends StockBaseDao<CategoryStock> {
	public static final String ID = "id";
	public static final String STOCK = "stock";
	public static final String CATEGORY = "category";
}
