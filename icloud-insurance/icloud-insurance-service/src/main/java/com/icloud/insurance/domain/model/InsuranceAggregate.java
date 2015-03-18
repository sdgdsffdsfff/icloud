package com.icloud.insurance.domain.model;

import java.util.Date;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.InsuranceNumberService;
import com.icloud.insurance.service.InsuranceObjectService;
import com.icloud.insurance.util.InsuranceUtil;

public class InsuranceAggregate extends AggregateRoot {
	private String insuranceName;
	private String insuranceCompany;
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

	public InsuranceAggregate() {
	}

	public static InsuranceAggregate convertInsuranceAggregateFromInsuranceProduct(
			InsuranceProduct product,
			InsuranceNumberService insuranceNumberService,
			InsuranceObjectService insuranceObjectService, boolean lazyLoading) {
		InsuranceAggregate aggreate = new InsuranceAggregate();
		aggreate = ICloudUtils.dozerCopy(aggreate, product);
		aggreate.setInsuranceNumberService(insuranceNumberService);
		aggreate.setInsuranceObjectService(insuranceObjectService);
		aggreate.setLazyLoading(lazyLoading);
		if (!lazyLoading) {
			aggreate.lazyLoading();
		}
		return aggreate;
	}

	private void lazyLoading() {
		this.loadingUnderwritingAge();
		this.loadingInsuranceBaseInfo();
		this.loadingInsuranceHightLights();
	}

	public static InsuranceAggregate convertInsuranceAggregateFromInsuranceProduct(
			InsuranceProduct product,
			InsuranceNumberService insuranceNumberService,
			InsuranceObjectService InsuranceObjectService) {
		return convertInsuranceAggregateFromInsuranceProduct(product,
				insuranceNumberService, InsuranceObjectService, true);
	}

	public void updateUnderwritingAge() {
		this.insuranceNumberService.saveUnderwritingAge(this.id,
				underwritingAge);
	}

	public void updateInsuranceHightLights() {
		this.insuranceObjectService.saveInsuranceHightLights(this.id,
				this.insuranceHightLights);
	}

	public void updateInsuranceBaseInfo() {
		this.insuranceObjectService.saveInsuranceBaseInfo(this.id,
				this.insuranceBaseInfo);
	}

	public UnderwritingAge getUnderwritingAge() {
		if (!ICloudUtils.isNotNull(underwritingAge) && lazyLoading) {
			loadingUnderwritingAge();
		}
		return this.underwritingAge;
	}

	public InsuranceBaseInfo getInsuranceBaseInfo() {
		if (!ICloudUtils.isNotNull(insuranceBaseInfo) && lazyLoading) {
			loadingInsuranceBaseInfo();
		}
		return this.insuranceBaseInfo;
	}

	public InsuranceHightLights getInsuranceHightLights() {
		if (!ICloudUtils.isNotNull(insuranceHightLights) && lazyLoading) {
			loadingInsuranceHightLights();
		}
		return this.insuranceHightLights;
	}

	private void loadingUnderwritingAge() {
		this.underwritingAge = this.insuranceNumberService
				.getUnderwritingAge(this.id);
	}

	private void loadingInsuranceBaseInfo() {
		this.insuranceBaseInfo = this.insuranceObjectService
				.getInsuranceBaseInfo(this.id);
	}

	private void loadingInsuranceHightLights() {
		this.insuranceHightLights = this.insuranceObjectService
				.getInsuranceHightLights(this.id);
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

	public void setInsuranceObjectService(
			InsuranceObjectService insuranceObjectService) {
		this.insuranceObjectService = insuranceObjectService;
	}

	public void setInsuranceHightLights(
			InsuranceHightLights insuranceHightLights) {
		this.insuranceHightLights = insuranceHightLights;
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
