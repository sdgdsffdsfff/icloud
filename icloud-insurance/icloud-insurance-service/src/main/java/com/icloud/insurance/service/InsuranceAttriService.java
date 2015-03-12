package com.icloud.insurance.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.insurance.dao.InsuranceAttriDao;
import com.icloud.insurance.model.InsuranceAttri;

@Service("insuranceAttriService")
public class InsuranceAttriService extends SqlBaseService<InsuranceAttri> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceAttriService.class);

	@Resource(name = "insuranceAttriDao")
	private InsuranceAttriDao insuranceAttriDao;

	@Override
	protected IHibernateBaseDao<InsuranceAttri> getDao() {
		return insuranceAttriDao;
	}

}
