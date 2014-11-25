package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.MarketingEmail;

public interface IMarketingEmailDao extends StockBaseDao<MarketingEmail> {
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String DITCH = "ditch";
	public static final String ACTIVE = "active";
	public static final String ACCESSCOUNT = "accessCount";
	public static final String LASTUPDATETIME = "lastUpdateTime";
	public static final String LASTSENDTIME = "lastSendTime";
	public static final String ACCCESSDETAIL = "acccessDetail";
}
