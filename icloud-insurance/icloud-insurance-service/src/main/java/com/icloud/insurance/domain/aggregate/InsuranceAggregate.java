package com.icloud.insurance.domain.aggregate;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;
import com.icloud.insurance.domain.entity.InsuranceBaseInfo;
import com.icloud.insurance.domain.entity.InsuranceHightLights;
import com.icloud.insurance.domain.entity.UnderwritingAge;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.InsuranceNumberService;
import com.icloud.insurance.service.InsuranceObjectService;
import com.icloud.insurance.util.InsuranceUtil;

public class InsuranceAggregate extends AggregateRoot {
	private int id;
	private String insuranceName;
	private String insuranceCompany;
	private Integer insuranceCompanyId;
	private String simpleDescription;
	private Integer safeguardTime;
	private Date createTime;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;
	private String lastUpdateUserName;
	private Integer insuranceStatus;
	private Integer insuranceCategoryId;
	/**
	 * private 承保年龄
	 */
	private UnderwritingAge underwritingAge;
	/**
	 * private baseinfo
	 */
	private InsuranceBaseInfo insuranceBaseInfo;
	/**
	 * 高亮
	 */
	private InsuranceHightLights insuranceHightLights;

	private InsuranceNumberService insuranceNumberService;
	private InsuranceObjectService insuranceObjectService;

	private boolean lazyLoading = true;

	public InsuranceAggregate(int aggregateId, boolean lazyLoading,
			InsuranceNumberService insuranceNumberService,
			InsuranceObjectService insuranceObjectService)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(aggregateId);
		this.lazyLoading = lazyLoading;
		this.insuranceNumberService = insuranceNumberService;
		this.insuranceObjectService = insuranceObjectService;
		init();
	}

	private void init() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		insuranceBaseInfo = InsurnaceBaseDomainEntity.getInsurance(
				InsuranceBaseInfo.class, this, InsuranceObjectService.class,
				this.insuranceObjectService, lazyLoading);

		underwritingAge = InsurnaceBaseDomainEntity.getInsurance(
				UnderwritingAge.class, this, InsuranceNumberService.class,
				this.insuranceNumberService, lazyLoading);

		insuranceHightLights = InsurnaceBaseDomainEntity.getInsurance(
				InsuranceHightLights.class, this, InsuranceObjectService.class,
				this.insuranceObjectService, lazyLoading);
	}

	public void deletAllAttribute() {
		insuranceBaseInfo.deleteEntity();
		underwritingAge.deleteEntity();
		insuranceHightLights.deleteEntity();
	}

	public static InsuranceAggregate convertInsuranceAggregateFromInsuranceProduct(
			InsuranceProduct product,
			InsuranceNumberService insuranceNumberService,
			InsuranceObjectService insuranceObjectService, boolean lazyLoading) {
		InsuranceAggregate aggregate = null;
		try {
			aggregate = new InsuranceAggregate(product.getId(), lazyLoading,
					insuranceNumberService, insuranceObjectService);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ICloudUtils.isNotNull(aggregate)) {
			// aggreate = ICloudUtils.dozerCopy(aggreate, product);
			aggregate.setId(product.getId());
			aggregate.setInsuranceName(product.getInsuranceName());
			aggregate.setInsuranceCompany(product.getInsuranceCompany());
			aggregate.setInsuranceCompanyId(product.getInsuranceCompanyId());
			aggregate.setSimpleDescription(product.getSimpleDescription());
			aggregate.setSafeguardTime(product.getSafeguardTime());
			aggregate.setCreateTime(product.getCreateTime());
			aggregate.setLastUpdateTime(product.getLastUpdateTime());
			aggregate.setLastUpdateUserId(product.getLastUpdateUserId());
			aggregate.setLastUpdateUserName(product.getLastUpdateUserName());
			aggregate.setInsuranceStatus(product.getInsuranceStatus());
			aggregate.setInsuranceCategoryId(product.getInsuranceCategoryId());
		}
		return aggregate;
	}

	public static InsuranceAggregate convertInsuranceAggregateFromInsuranceProduct(
			InsuranceProduct product,
			InsuranceNumberService insuranceNumberService,
			InsuranceObjectService InsuranceObjectService) {
		return convertInsuranceAggregateFromInsuranceProduct(product,
				insuranceNumberService, InsuranceObjectService, true);
	}

	public UnderwritingAge getUnderwritingAge() {
		return this.underwritingAge;
	}

	public InsuranceBaseInfo getInsuranceBaseInfo() {
		return this.insuranceBaseInfo;
	}

	public void setInsuranceBaseInfo(InsuranceBaseInfo insuranceBaseInfo) {
		this.insuranceBaseInfo = insuranceBaseInfo;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getSimpleDescription() {
		return simpleDescription;
	}

	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}

	public Integer getSafeguardTime() {
		return safeguardTime;
	}

	public void setSafeguardTime(Integer safeguardTime) {
		this.safeguardTime = safeguardTime;
	}

	public Integer getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(Integer insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public Integer getInsuranceCategoryId() {
		return insuranceCategoryId;
	}

	public void setInsuranceCategoryId(Integer insuranceCategoryId) {
		this.insuranceCategoryId = insuranceCategoryId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getSafeTimeForString() {
		return InsuranceUtil.convertTimeToSafeguardTime(this.safeguardTime);
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public void setLastUpdateUserName(String lastUpdateUserName) {
		this.lastUpdateUserName = lastUpdateUserName;
	}

	public void setUnderwritingAge(UnderwritingAge underwritingAge) {
		this.underwritingAge = underwritingAge;
	}

	public InsuranceNumberService getInsuranceNumberService() {
		return insuranceNumberService;
	}

	public void setInsuranceNumberService(
			InsuranceNumberService insuranceNumberService) {
		this.insuranceNumberService = insuranceNumberService;
	}

	public boolean isLazyLoading() {
		return lazyLoading;
	}

	public void setLazyLoading(boolean lazyLoading) {
		this.lazyLoading = lazyLoading;
	}

	public InsuranceObjectService getInsuranceObjectService() {
		return insuranceObjectService;
	}

	public InsuranceHightLights getInsuranceHightLights() {
		return this.insuranceHightLights;
	}

	public void setInsuranceObjectService(
			InsuranceObjectService insuranceObjectService) {
		this.insuranceObjectService = insuranceObjectService;
	}

	public void setInsuranceHightLights(
			InsuranceHightLights insuranceHightLights) {
		this.insuranceHightLights = insuranceHightLights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	@Override
	public String toString() {
		return "InsuranceAggregate [id=" + this.aggregateId
				+ ", insuranceName=" + insuranceName + ", insuranceCompany="
				+ insuranceCompany + ", simpleDescription=" + simpleDescription
				+ ", safeguardTime=" + safeguardTime + ", createTime="
				+ createTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", lastUpdateUserId=" + lastUpdateUserId
				+ ", lastUpdateUserName=" + lastUpdateUserName
				+ ", insuranceStatus=" + insuranceStatus
				+ ", insuranceCategoryId=" + insuranceCategoryId + "]";
	}

}
