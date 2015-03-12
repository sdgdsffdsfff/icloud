package com.icloud.insurance.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.insurance.dao.InsuranceProductDao;
import com.icloud.insurance.model.InsuranceProduct;

@Service("insuranceProductService")
public class InsuranceProductService extends SqlBaseService<InsuranceProduct> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceProductService.class);

	@Resource(name = "insuranceProductDao")
	private InsuranceProductDao insuranceProductDao;

	@Override
	protected IHibernateBaseDao<InsuranceProduct> getDao() {
		return insuranceProductDao;
	}

}
