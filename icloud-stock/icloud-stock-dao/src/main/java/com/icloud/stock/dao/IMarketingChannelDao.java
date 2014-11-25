package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.MarketingChannel;

public interface IMarketingChannelDao extends StockBaseDao<MarketingChannel> {
	public static final String ID = "id";
	public static final String CHANNELNAME = "channelName";
}
