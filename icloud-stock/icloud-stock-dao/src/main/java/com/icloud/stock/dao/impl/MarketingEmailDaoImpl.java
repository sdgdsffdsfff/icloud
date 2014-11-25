package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IMarketingEmailDao;
import com.icloud.stock.model.MarketingEmail;

@Repository("marketingEmailDao")
public class MarketingEmailDaoImpl extends StockBaseDaoImpl<MarketingEmail>
		implements IMarketingEmailDao {

}
