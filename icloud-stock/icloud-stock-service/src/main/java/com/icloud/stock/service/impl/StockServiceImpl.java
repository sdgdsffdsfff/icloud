package com.icloud.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IStockDao;
import com.icloud.stock.model.Stock;
import com.icloud.stock.service.IStockService;

@Service("stockService")
public class StockServiceImpl extends SqlBaseService<Stock> implements
		IStockService {

	@Resource(name = "stockDao")
	private IStockDao stockDao;

	@Override
	protected IHibernateBaseDao<Stock> getDao() {
		// TODO Auto-generated method stub
		return stockDao;
	}
}
