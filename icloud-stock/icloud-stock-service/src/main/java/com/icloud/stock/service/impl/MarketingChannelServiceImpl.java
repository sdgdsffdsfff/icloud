package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IMarketingChannelDao;
import com.icloud.stock.model.MarketingChannel;
import com.icloud.stock.service.IMarketingChannelService;

@Service("marketingChannelService")
public class MarketingChannelServiceImpl extends
		SqlBaseService<MarketingChannel> implements IMarketingChannelService {

	@Resource(name = "marketingChannelDao")
	private IMarketingChannelDao marketingChannelDao;

	@Override
	protected IHibernateBaseDao<MarketingChannel> getDao() {
		return this.marketingChannelDao;
	}

}
