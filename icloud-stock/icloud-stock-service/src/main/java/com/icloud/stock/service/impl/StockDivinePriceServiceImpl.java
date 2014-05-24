package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IStockDivinePriceDao;
import com.icloud.stock.model.StockDivinePrice;
import com.icloud.stock.service.IStockDivinePriceService;

@Service("stockDivinePriceService")
public class StockDivinePriceServiceImpl extends
		SqlBaseService<StockDivinePrice> implements IStockDivinePriceService {

	@Resource(name = "stockDivinePriceDao")
	private IStockDivinePriceDao stockDivinePriceDao;

	@Override
	protected IHibernateBaseDao<StockDivinePrice> getDao() {
		// TODO Auto-generated method stub
		return stockDivinePriceDao;
	}

}
