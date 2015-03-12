package com.icloud.insurance.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.insurance.dao.InsuranceObjectDao;
import com.icloud.insurance.model.InsuranceObject;

@Service("insuranceObjectService")
public class InsuranceObjectService extends SqlBaseService<InsuranceObject> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceObjectService.class);

	@Resource(name = "insuranceObjectDao")
	private InsuranceObjectDao insuranceObjectDao;

	@Override
	protected IHibernateBaseDao<InsuranceObject> getDao() {
		return insuranceObjectDao;
	}
	

}
