package com.icloud.stock.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.core.common.ReturnCode;
import com.icloud.framework.core.exception.ICloudException;
import com.icloud.framework.core.time.DateTimeUtil;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IStockDateHistoryDao;
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
	public boolean isUpdateNowInChinaStock(Integer id) {
		if (!ICloudUtils.isNotNull(id)) {
			throw ICloudException.instance(ReturnCode.E_IS_NULL);
		}
		Date date = getMaxUpdateTime(id);
		if (date == null)
			return true;

		Date lastWorkDate = ChinaStockUtil.getLastWorkDate(new Date());

		return !DateTimeUtil.isSameDay(date, lastWorkDate);
	}

	@Override
	public void deleteByStockId(Integer id) {
		this.stockHistoryDao.deleteByStockId(id);
	}

}
