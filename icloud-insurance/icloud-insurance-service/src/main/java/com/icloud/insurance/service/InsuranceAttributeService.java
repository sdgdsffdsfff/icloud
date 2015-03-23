package com.icloud.insurance.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.security.MD5;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.dao.InsuranceAttributeDao;
import com.icloud.insurance.db.InitDB;
import com.icloud.insurance.domain.valueobject.InsuranceAggregateValueObject;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.InsuranceInfoEnum;
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
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.SAFEGUARDAGE
				.getUuid());
	}

	public int getSafeGuardTimeKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.SAFEGUARDTIME
				.getUuid());
	}

	public int getSuitePeopleKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.SUITEPEOPLE
				.getUuid());
	}

	public int getProductHightLightsKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.PRODUCTHIGHLIGHTS
				.getUuid());
	}

	public int getProductFeatureKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.PRODUCTFEATURES
				.getUuid());
	}

	public int getProductTipsKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.PRODUCTTIPS
				.getUuid());
	}

	public int getProductRecommendKey() {
		return getInsuranceAttributeIdFromUUID(InsuranceInfoEnum.RECOMMEND_TIPS
				.getUuid());
	}

	public int getInsuranceCompanyKey() {
		return getInsuranceAttributeIdFromUUID(SystemMenuEnum.INSURANCECOMPANY
				.getUuid());
	}

	public InsuranceAttribute getInsuranceCompany() {
		return this.getByUUID(SystemMenuEnum.INSURANCECOMPANY.getUuid());
	}

	public List<InsuranceAttribute> findAllInsuranceCompany() {
		return this.findByProperies(InsuranceAttributeConstant.FATHERID,
				getInsuranceCompanyKey());
	}

	public boolean exitsInsuranceCompany(String uuid) {
		return ICloudUtils.isNotNull(getInsuranceCompany(uuid));
	}

	public InsuranceAttribute saveCompany(String companyName, String url) {
		if (ICloudUtils.isNotNull(companyName) && ICloudUtils.isNotNull(url)) {
			InsuranceAttribute fatherInsuranceAttribute = getInsuranceCompany();
			InsuranceAttribute insuranceAttribute = new InsuranceAttribute();
			insuranceAttribute.setAttributeName(companyName);
			insuranceAttribute.setFatherId(fatherInsuranceAttribute.getId());
			insuranceAttribute.setFatherName(fatherInsuranceAttribute
					.getAttributeName());
			insuranceAttribute.setStatus(SystemStatusEnum.OK.getStatus());
			insuranceAttribute
					.setLevel(fatherInsuranceAttribute.getLevel() + 1);
			insuranceAttribute.setUuid(MD5.MD5Encode(companyName));
			insuranceAttribute.setDescription(url);
			this.save(insuranceAttribute);
			return insuranceAttribute;
		}
		return null;
	}

	public boolean isCompany(InsuranceAttribute insuranceAttribute) {
		if (ICloudUtils.isNotNull(insuranceAttribute)
				&& ICloudUtils.isNotNull(insuranceAttribute.getFatherId())) {
			if (insuranceAttribute.getFatherId().equals(
					getInsuranceCompanyKey())) {
				return true;
			}
		}
		return false;
	}

	public InsuranceAttribute getInsuranceCompany(String uuid) {
		InsuranceAttribute insuranceAttribute = getByUUID(uuid);
		if (isCompany(insuranceAttribute))
			return insuranceAttribute;
		return null;
	}

	public InsuranceAttribute getInsuranceCompany(int id) {
		InsuranceAttribute insuranceAttribute = getById(id);
		if (isCompany(insuranceAttribute))
			return insuranceAttribute;
		return null;
	}

	public void saveOrUpdateCompany(int companyId, String companyName,
			String imgUuid) {
		InsuranceAttribute insuranceAttribute = getInsuranceCompany(companyId);
		if (ICloudUtils.isNotNull(insuranceAttribute)) {
			insuranceAttribute.setAttributeName(companyName);
			insuranceAttribute.setUuid(MD5.encode(companyName));
			insuranceAttribute.setDescription(imgUuid);
			this.update(insuranceAttribute);
		}
	}
}
