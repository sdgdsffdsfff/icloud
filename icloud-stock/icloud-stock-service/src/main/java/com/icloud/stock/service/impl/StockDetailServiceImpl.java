package com.icloud.stock.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IStockDetailDao;
import com.icloud.stock.dao.impl.StockDaoImpl;
import com.icloud.stock.dao.impl.StockDetailDaoImpl;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDetail;
import com.icloud.stock.service.IStockDetailService;

@Service("stockDetailService")
public class StockDetailServiceImpl extends SqlBaseService<StockDetail>
		implements IStockDetailService {

	@Resource(name = "stockDetailDao")
	private IStockDetailDao stockDetailDao;

	@Override
	protected IHibernateBaseDao<StockDetail> getDao() {
		// TODO Auto-generated method stub
		return this.stockDetailDao;
	}

	@Override
	public StockDetail getStockByStockCode(String stockCode) {
		List<StockDetail> list = this.stockDetailDao.findByProperty(
				StockDetailDaoImpl.STOCKCODE, stockCode, 0, 1);
		return ICloudUtils.getFirstElement(list);
	}

	@Override
	public StockDetail getStockByStockId(Integer stockId) {
		List<StockDetail> list = this.stockDetailDao.findByProperty(
				StockDetailDaoImpl.STOCKID, stockId, 0, 1);
		return ICloudUtils.getFirstElement(list);
	}

}
