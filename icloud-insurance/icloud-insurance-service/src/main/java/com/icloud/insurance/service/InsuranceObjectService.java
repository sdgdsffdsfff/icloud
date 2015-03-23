package com.icloud.insurance.service;

import java.util.ArrayList;
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
import com.icloud.insurance.domain.entity.InsuranceBaseInfo;
import com.icloud.insurance.domain.entity.InsuranceHightLights;
import com.icloud.insurance.domain.valueobject.InsuranceAggregateValueObject;
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

	public List<InsuranceObject> getInsuranceObjectList(int productId, int key) {
		String[] paramNames = { InsuranceObjectConstant.INSURANCEID,
				InsuranceObjectConstant.INSURANCEKEY };
		Object[] values = { productId, key };
		return this.insuranceObjectDao.findByProperty(paramNames, values,
				InsuranceObjectConstant.INSURANCEORDER, true);
	}

	private void deleteInsuranceObject(int productId, int key) {
		List<InsuranceObject> objects = getInsuranceObjectList(productId, key);
		if (!ICloudUtils.isEmpty(objects)) {
			for (InsuranceObject object : objects) {
				this.delete(object);
			}
		}
	}

	public List<String> getInsuranceObjectForString(int productId, int key) {
		List<InsuranceObject> objects = getInsuranceObjectList(productId, key);
		if (!ICloudUtils.isEmpty(objects)) {
			List<String> list = new ArrayList<String>();
			for (InsuranceObject object : objects) {
				list.add(object.getInsuranceValue());
			}
			return list;
		}
		return null;
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
		if (ICloudUtils.isNotNull(value)) {
			InsuranceObject insuranceNumber = getInsuranceObject(productId,
					key, order);
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
		return null;
	}

	private void saveOrUpdateInsuranceObject(Integer productId, int key,
			List<String> list) {
		if (ICloudUtils.isNotNull(productId) && ICloudUtils.isNotNull(list)) {
			int i = 0;
			for (String value : list) {
				saveOrUpdateInsuranceObject(productId, key, value, i++);
			}
		}

	}

	public InsuranceBaseInfo getInsuranceBaseInfo(Integer insuranceId,
			InsuranceBaseInfo baseInfo) {
		if (ICloudUtils.isNotNull(insuranceId)
				&& ICloudUtils.isNotNull(baseInfo)) {
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

			List<String> productFeatures = this.getInsuranceObjectForString(
					insuranceId,
					InsuranceAggregateValueObject.PORDUCTFEATURES_KEY);
			baseInfo.setProductFeatures(productFeatures);

			insuranceObject = this.getInsuranceObject(insuranceId,
					InsuranceAggregateValueObject.PRODUCT_RECOMMEND_KEY, 0);
			if (ICloudUtils.isNotNull(insuranceObject)) {
				baseInfo.setSpecRecommend(insuranceObject.getInsuranceValue());
			}
			return baseInfo;
		}
		return null;
	}

	public void saveInsuranceBaseInfo(Integer productId,
			InsuranceBaseInfo insuranceBaseInfo) {
		if (ICloudUtils.isNotNull(productId)
				&& ICloudUtils.isNotNull(insuranceBaseInfo)) {
			if (ICloudUtils.isNotNull(insuranceBaseInfo.getSafeguardTimeDesc())) {
				saveOrUpdateInsuranceObject(productId,
						InsuranceAggregateValueObject.SAFEGUARDTIME_KEY,
						insuranceBaseInfo.getSafeguardTimeDesc(), 0);
			}
			if (ICloudUtils.isNotNull(insuranceBaseInfo.getSuitePeopleDesc())) {
				saveOrUpdateInsuranceObject(productId,
						InsuranceAggregateValueObject.SUITEPEOPLE_KEY,
						insuranceBaseInfo.getSuitePeopleDesc(), 0);
			}
			saveOrUpdateInsuranceObject(productId,
					InsuranceAggregateValueObject.PORDUCTFEATURES_KEY,
					insuranceBaseInfo.getProductFeatures());
			saveOrUpdateInsuranceObject(productId,
					InsuranceAggregateValueObject.PRODUCTTIPS_KEY,
					insuranceBaseInfo.getTips());
			saveOrUpdateInsuranceObject(productId,
					InsuranceAggregateValueObject.PRODUCT_RECOMMEND_KEY,
					insuranceBaseInfo.getSpecRecommend(), 0);
		}
	}

	public void deleteInsuranceBaseInfo(int productId,
			InsuranceBaseInfo insuranceBaseInfo) {
		if (ICloudUtils.isNotNull(productId)
				&& ICloudUtils.isNotNull(insuranceBaseInfo)) {
			this.deleteInsuranceObject(productId,
					InsuranceAggregateValueObject.SAFEGUARDTIME_KEY);
			this.deleteInsuranceObject(productId,
					InsuranceAggregateValueObject.SUITEPEOPLE_KEY);
			this.deleteInsuranceObject(productId,
					InsuranceAggregateValueObject.PORDUCTFEATURES_KEY);
			this.deleteInsuranceObject(productId,
					InsuranceAggregateValueObject.PRODUCTTIPS_KEY);
			this.deleteInsuranceObject(productId,
					InsuranceAggregateValueObject.PRODUCT_RECOMMEND_KEY);
		}
	}

	public void saveInsuranceHightLights(Integer productId,
			InsuranceHightLights insuranceHightLights) {
		if (ICloudUtils.isNotNull(productId)
				&& ICloudUtils.isNotNull(insuranceHightLights)) {
			saveOrUpdateInsuranceObject(productId,
					InsuranceAggregateValueObject.PRODUCTHIGHLIGHTS_KEY,
					insuranceHightLights.getHighlights());
		}
	}

	public InsuranceHightLights getInsuranceHightLights(Integer productId,
			InsuranceHightLights insuranceHightLights) {
		if (ICloudUtils.isNotNull(productId)
				&& ICloudUtils.isNotNull(insuranceHightLights)) {
			List<String> highlights = getInsuranceObjectForString(productId,
					InsuranceAggregateValueObject.PRODUCTHIGHLIGHTS_KEY);
			insuranceHightLights.setHighlights(highlights);
			return insuranceHightLights;
		}
		return null;
	}

	public void deleteInsuranceHightLights(int productId,
			InsuranceHightLights insuranceHightLights) {
		this.deleteInsuranceObject(productId,
				InsuranceAggregateValueObject.PRODUCTHIGHLIGHTS_KEY);
	}

}
