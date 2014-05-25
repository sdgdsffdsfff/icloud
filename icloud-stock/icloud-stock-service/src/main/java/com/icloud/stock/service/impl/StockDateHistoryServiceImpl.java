package com.icloud.stock.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.icloud.framework.core.common.ReturnCode;
import com.icloud.framework.core.exception.ICloudException;
import com.icloud.framework.core.time.DateTimeUtil;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.entity.StockDataHistoryUpdateCriteria;
import com.icloud.front.stock.entity.StockUpdateOperation.StockDataHistoryTableStatus;
import com.icloud.stock.dao.IStockDateHistoryDao;
import com.icloud.stock.dao.impl.StockDateHistoryDaoImpl;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.service.IStockDateHistoryService;
import com.icloud.stock.util.ChinaStockUtil;

@Service("stockDateHistoryService")
public class StockDateHistoryServiceImpl extends
		SqlBaseService<StockDateHistory> implements IStockDateHistoryService {

	@Resource(name = "stockDateHistoryDao")
	private IStockDateHistoryDao stockHistoryDao;

	@Override
	protected IHibernateBaseDao<StockDateHistory> getDao() {
		// TODO Auto-generated method stub
		return stockHistoryDao;
	}

	@Override
	public Date getMaxUpdateTime(Integer id) {
		return this.stockHistoryDao.getMaxUpdateTime(id);
	}

	@Override
	public StockDataHistoryUpdateCriteria getChinaStockDataHistoryTableStatus(
			Integer id) {
		if (!ICloudUtils.isNotNull(id)) {
			throw ICloudException.instance(ReturnCode.E_IS_NULL);
		}
		Date startdate = getMaxUpdateTime(id);
		if (startdate == null) {
			return new StockDataHistoryUpdateCriteria(null, null,
					StockDataHistoryTableStatus.NO);
		}
		Date endDate = ChinaStockUtil.getLastWorkDate(new Date());

		if (DateTimeUtil.isSameDay(startdate, endDate)) {
			return new StockDataHistoryUpdateCriteria(null, null,
					StockDataHistoryTableStatus.OK);
		}
		return new StockDataHistoryUpdateCriteria(DateUtils.addDays(startdate,
				1), endDate, StockDataHistoryTableStatus.UPDATE);
	}

	@Override
	public void deleteByStockId(Integer id) {
		this.stockHistoryDao.deleteByStockId(id);
	}

	@Override
	public List<StockDateHistory> findByStockId(Integer id) {
		return this.stockHistoryDao.findByStockId(id);
	}

	@Override
	public int countByStockId(Integer id) {
		return (int) this.stockHistoryDao.countByProperty(
				StockDateHistoryDaoImpl.STOCK_ID, id);
	}

	@Override
	public List<StockDateHistory> findByStockId(Integer id, int start, int limit) {
		return this.stockHistoryDao.findByStockId(id, start, limit);
	}

}
