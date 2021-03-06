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
import com.icloud.insurance.dao.InsuranceNumberDao;
import com.icloud.insurance.domain.entity.UnderwritingAge;
import com.icloud.insurance.domain.valueobject.InsuranceAggregateValueObject;
import com.icloud.insurance.model.InsuranceNumber;
import com.icloud.insurance.model.constant.InsuranceNumberConstant;

@Service("insuranceNumberService")
public class InsuranceNumberService extends SqlBaseService<InsuranceNumber> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceNumberService.class);

	@Resource(name = "insuranceNumberDao")
	private InsuranceNumberDao insuranceNumberDao;

	@Resource(name = "insuranceAttributeService")
	private InsuranceAttributeService insuranceAttributeService;

	@Override
	protected IHibernateBaseDao<InsuranceNumber> getDao() {
		return insuranceNumberDao;
	}

	public InsuranceNumber getInsuranceNumber(int productId, int key, int order) {
		String[] paramNames = { InsuranceNumberConstant.INSURANCEID,
				InsuranceNumberConstant.INSURANCEKEY,
				InsuranceNumberConstant.INSURANCEORDER };
		OperationEnum[] operations = { OperationEnum.EQUALS,
				OperationEnum.EQUALS, OperationEnum.EQUALS };
		Object[] values = { productId, key, order };
		return ICloudUtils.getFirstElement(this.insuranceNumberDao
				.findByPropertyNoLazy(paramNames, operations, values, null,
						true, 0, 2));
	}

	public List<InsuranceNumber> getInsuranceNumberList(int productId, int key) {
		String[] paramNames = { InsuranceNumberConstant.INSURANCEID,
				InsuranceNumberConstant.INSURANCEKEY };
		Object[] values = { productId, key };
		return this.insuranceNumberDao.findByProperty(paramNames, values,
				InsuranceNumberConstant.INSURANCEORDER, true);
	}

	private void deleteInsuranceNumber(int productId, int key) {
		List<InsuranceNumber> objects = getInsuranceNumberList(productId, key);
		if (!ICloudUtils.isEmpty(objects)) {
			for (InsuranceNumber object : objects) {
				this.delete(object);
			}
		}
	}

	public InsuranceNumber saveOrUpdateInsuranceNumber(int productId, int key,
			int value, int order, String desc) {
		InsuranceNumber insuranceNumber = getInsuranceNumber(productId, key,
				order);
		if (!ICloudUtils.isNotNull(insuranceNumber)) {
			insuranceNumber = new InsuranceNumber();
			insuranceNumber.setInsuranceOrder(order);
			insuranceNumber.setInsuranceValue(value);
			insuranceNumber.setInsuranceDescription(desc);
			insuranceNumber.setInsuranceId(productId);
			insuranceNumber.setInsuranceKey(key);
			this.insuranceNumberDao.save(insuranceNumber);
		} else {
			insuranceNumber.setInsuranceOrder(order);
			insuranceNumber.setInsuranceValue(value);
			insuranceNumber.setInsuranceDescription(desc);
			insuranceNumber.setInsuranceId(productId);
			insuranceNumber.setInsuranceKey(key);
			this.insuranceNumberDao.update(insuranceNumber);
		}
		return insuranceNumber;
	}

	public UnderwritingAge getUnderwritingAge(Integer productId,
			UnderwritingAge age) {
		if (ICloudUtils.isNotNull(productId) && ICloudUtils.isNotNull(age)) {
			InsuranceNumber firstInsuranceNumber = getInsuranceNumber(
					productId,
					InsuranceAggregateValueObject.UNDER_WRITING_AGE_KEY, 0);
			if (ICloudUtils.isNotNull(firstInsuranceNumber)) {
				InsuranceNumber endInsuranceNumber = getInsuranceNumber(
						productId,
						InsuranceAggregateValueObject.UNDER_WRITING_AGE_KEY, 1);
				age.setStartAge(firstInsuranceNumber.getInsuranceValue());
				if (ICloudUtils.isNotNull(endInsuranceNumber)) {
					age.setEndAge(endInsuranceNumber.getInsuranceValue());
				}
				return age;
			}
		}
		return null;
	}

	public void saveUnderwritingAge(Integer productId, UnderwritingAge age) {
		if (ICloudUtils.isNotNull(productId) && ICloudUtils.isNotNull(age)) {
			if (age.getStartAge() != ICloudUtils.DEFAULT_INT_VALUE) {
				saveOrUpdateInsuranceNumber(productId,
						InsuranceAggregateValueObject.UNDER_WRITING_AGE_KEY,
						age.getStartAge(), 0, null);
				if (age.getEndAge() != ICloudUtils.DEFAULT_INT_VALUE) {
					saveOrUpdateInsuranceNumber(
							productId,
							InsuranceAggregateValueObject.UNDER_WRITING_AGE_KEY,
							age.getEndAge(), 1, null);
				}
			}
		}
	}

	public void deleteUnderwritingAge(int productId,
			UnderwritingAge underwritingAge) {
		this.deleteInsuranceNumber(productId,
				InsuranceAggregateValueObject.UNDER_WRITING_AGE_KEY);
	}
}
