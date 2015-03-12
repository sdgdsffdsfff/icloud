package com.icloud.insurance.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.insurance.dao.InsuranceNumberDao;
import com.icloud.insurance.model.InsuranceNumber;

@Service("insuranceNumberService")
public class InsuranceNumberService extends SqlBaseService<InsuranceNumber> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceNumberService.class);

	@Resource(name = "insuranceNumberDao")
	private InsuranceNumberDao insuranceNumberDao;

	@Override
	protected IHibernateBaseDao<InsuranceNumber> getDao() {
		return insuranceNumberDao;
	}

}
