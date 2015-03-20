package com.icloud.insurance.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.dao.InsuranceAttributeDao;
import com.icloud.insurance.db.InitDB;
import com.icloud.insurance.domain.valueobject.InsuranceAggregateValueObject;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemMenuEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemStatusEnum;
import com.icloud.insurance.model.InsuranceAttribute;
import com.icloud.insurance.model.constant.InsuranceAttributeConstant;

@Service("insuranceAttributeService")
public class InsuranceAttributeService extends
		SqlBaseService<InsuranceAttribute> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceAttributeService.class);

	@Resource(name = "insuranceAttributeDao")
	private InsuranceAttributeDao insuranceAttributeDao;

	@Override
	protected IHibernateBaseDao<InsuranceAttribute> getDao() {
		return insuranceAttributeDao;
	}

	@PostConstruct
	public void init() {
		InitDB.init(this);
		InsuranceAggregateValueObject.init(this);
	}

	public boolean existsUUID(String uuid) {
		return !ICloudUtils.isNotNull(getByUUID(uuid));
	}

	public InsuranceAttribute getByUUID(String uuid) {
		if (ICloudUtils.isNotNull(uuid)) {
			return ICloudUtils.getFirstElement(this.insuranceAttributeDao
					.findByProperty(InsuranceAttributeConstant.UUID, uuid));
		}
		return null;
	}

	public int getInsuranceAttributeIdFromUUID(String uuid) {
		InsuranceAttribute insuranceAttribute = getByUUID(uuid);
		if (ICloudUtils.isNotNull(insuranceAttribute)) {
			return insuranceAttribute.getId();
		}
		return SystemStatusEnum.NO_EXIST.getStatus();
	}

	public int getUnderWritingAgeKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.SAFEGUARDAGE
				.getUuid());
	}

	public int getSafeGuardTimeKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.SAFEGUARDTIME
				.getUuid());
	}

	public int getSuitePeopleKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.SUITEPEOPLE
				.getUuid());
	}

	public int getProductHightLightsKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.PRODUCTHIGHLIGHTS
				.getUuid());
	}

	public int getProductFeatureKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.PRODUCTFEATURES
				.getUuid());
	}

	public int getProductTipsKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.PRODUCTTIPS
				.getUuid());
	}

	public int getProductRecommendKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.RECOMMEND_TIPS
				.getUuid());
	}
}
