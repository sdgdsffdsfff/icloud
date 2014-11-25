package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IMarketingEmailDao;
import com.icloud.stock.model.MarketingEmail;
import com.icloud.stock.service.IMarketingEmailService;

@Service("marketingEmailService")
public class MarketingEmailServiceImpl extends SqlBaseService<MarketingEmail>
		implements IMarketingEmailService {

	@Resource(name = "marketingEmailDao")
	private IMarketingEmailDao marketingEmailDao;

	@Override
	protected IHibernateBaseDao<MarketingEmail> getDao() {
		return this.marketingEmailDao;
	}

}
