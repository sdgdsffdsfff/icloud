package com.icloud.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IStockDao;
import com.icloud.stock.dao.impl.StockDaoImpl;
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

	@Override
	public Stock getByStockAllCode(String stockCode) {
		List<Stock> list = this.stockDao.findByProperty(
				StockDaoImpl.STOCKALLCODE, stockCode, 0, 1);
		if (!ICloudUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
