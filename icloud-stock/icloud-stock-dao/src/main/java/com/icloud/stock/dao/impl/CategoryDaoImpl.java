package com.icloud.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.ICategoryDao;
import com.icloud.stock.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends StockBaseDaoImpl<Category> implements
		ICategoryDao {

	@Override
	public Category getCategory(String categoryName, String type) {
		// List<Category> list = getHibernateTemplate()
		// .find("from "
		// + domainClass.getName()
		// +
		// " as model where model.categoryName = ? and model.categoryCategoryType= ? ",
		// categoryName, type);
		// .find("from "
		// + domainClass.getName()
		// +
		// " as model where model.categoryName = ? and model.categoryCategoryType= ? ",
		// categoryName, type);
		String params[] = { "categoryName", "categoryCategoryType" };
		String values[] = { categoryName, type };
		List<Category> list = this.findByProperty(params, values, null, false);
		if (ICloudUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
}
