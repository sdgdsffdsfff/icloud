package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IMarketingChannelDao;
import com.icloud.stock.model.MarketingChannel;

@Repository("marketingChannelDao")
public class MarketingChannelDaoImpl extends StockBaseDaoImpl<MarketingChannel>
		implements IMarketingChannelDao {

}
