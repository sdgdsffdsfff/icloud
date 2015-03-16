package com.icloud.insurance.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.dao.InsuranceObjectDao;
import com.icloud.insurance.domain.model.InsuranceBaseInfo;
import com.icloud.insurance.domain.valueobject.InsuranceAggregateValueObject;
import com.icloud.insurance.model.InsuranceNumber;
import com.icloud.insurance.model.InsuranceObject;
import com.icloud.insurance.model.constant.InsuranceObjectConstant;

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

	public List<InsuranceObject> getInsuranceObject(int productId, int key) {
		String[] paramNames = { InsuranceObjectConstant.INSURANCEID,
				InsuranceObjectConstant.INSURANCEKEY };
		Object[] values = { productId, key };
		return this.insuranceObjectDao.findByProperty(paramNames, values, null,
				true);
	}

	public InsuranceObject getInsuranceObject(int productId, int key, int order) {
		String[] paramNames = { InsuranceObjectConstant.INSURANCEID,
				InsuranceObjectConstant.INSURANCEKEY,
				InsuranceObjectConstant.INSURANCEORDER };
		OperationEnum[] operations = { OperationEnum.EQUALS,
				OperationEnum.EQUALS, OperationEnum.EQUALS };
		Object[] values = { productId, key, order };
		return ICloudUtils.getFirstElement(this.insuranceObjectDao
				.findByPropertyNoLazy(paramNames, operations, values, null,
						true, 0, 2));
	}

	public InsuranceObject saveOrUpdateInsuranceObject(int productId, int key,
			String value, int order) {
		InsuranceObject insuranceNumber = getInsuranceObject(productId, key,
				order);
		if (!ICloudUtils.isNotNull(insuranceNumber)) {
			insuranceNumber = new InsuranceObject();
			insuranceNumber.setInsuranceOrder(order);
			insuranceNumber.setInsuranceValue(value);
			insuranceNumber.setInsuranceId(productId);
			insuranceNumber.setInsuranceKey(key);
			this.insuranceObjectDao.save(insuranceNumber);
		} else {
			insuranceNumber.setInsuranceOrder(order);
			insuranceNumber.setInsuranceValue(value);
			insuranceNumber.setInsuranceId(productId);
			insuranceNumber.setInsuranceKey(key);
			this.insuranceObjectDao.update(insuranceNumber);
		}
		return insuranceNumber;
	}

	public InsuranceBaseInfo getInsuranceBaseInfo(Integer insuranceId) {
		if (ICloudUtils.isNotNull(insuranceId)) {
			InsuranceBaseInfo baseInfo = new InsuranceBaseInfo();
			InsuranceObject insuranceObject = this.getInsuranceObject(
					insuranceId,
					InsuranceAggregateValueObject.SAFEGUARDTIME_KEY, 0);
			if (ICloudUtils.isNotNull(insuranceObject)) {
				baseInfo.setSafeguardTimeDesc(insuranceObject
						.getInsuranceValue());
			}
			insuranceObject = this.getInsuranceObject(insuranceId,
					InsuranceAggregateValueObject.SUITEPEOPLE_KEY, 0);
			if (ICloudUtils.isNotNull(insuranceObject)) {
				baseInfo.setSuitePeopleDesc(insuranceObject.getInsuranceValue());
			}
			return baseInfo;
		}
		return null;
	}

}
