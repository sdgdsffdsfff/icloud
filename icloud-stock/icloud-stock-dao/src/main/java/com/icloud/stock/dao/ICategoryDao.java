package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.Category;

public interface ICategoryDao extends StockBaseDao<Category> {
	public static final String ID = "id";
	public static final String CATEGORYNAME = "categoryName";
	public static final String CATEGORYRANK = "categoryRank";
	public static final String CATEGORYCATEGORYTYPE = "categoryCategoryType";
	public static final String CATEGORYSTOCKS = "categoryStocks";

	Category getCategory(String categoryName, String type);

}
