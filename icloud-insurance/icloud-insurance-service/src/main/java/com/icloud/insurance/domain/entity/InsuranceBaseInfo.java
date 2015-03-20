package com.icloud.insurance.domain.entity;

import java.util.List;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;
import com.icloud.insurance.service.InsuranceObjectService;

public class InsuranceBaseInfo extends InsurnaceBaseDomainEntity {
	private InsuranceObjectService insuranceObjectService;
	private String safeguardTimeDesc;
	private String suitePeopleDesc;
	private List<String> productFeatures;
	private List<String> tips;
	private String specRecommend;

	public InsuranceBaseInfo(AggregateRoot root,
			InsuranceObjectService insuranceObjectService, boolean lazyLoading) {
		super(root, lazyLoading);
		this.insuranceObjectService = insuranceObjectService;
	}

	public InsuranceBaseInfo(AggregateRoot root,
			InsuranceObjectService insuranceObjectService) {
		this(root, insuranceObjectService, true);
	}

	@Override
	public void doLoadEntity() {
		this.insuranceObjectService.getInsuranceBaseInfo(
				this.aggregateRoot.getAggregateId(), this);
	}

	public String getSafeguardTimeDesc() {
		// checkLoad();
		return safeguardTimeDesc;
	}

	public void setSafeguardTimeDesc(String safeguardTimeDesc) {
		this.safeguardTimeDesc = safeguardTimeDesc;
	}

	public String getSuitePeopleDesc() {
		checkLoad();
		return suitePeopleDesc;
	}

	public void setSuitePeopleDesc(String suitePeopleDesc) {
		this.suitePeopleDesc = suitePeopleDesc;
	}

	public List<String> getProductFeatures() {
		// checkLoad();
		System.out.println("nihao -------------");
		return productFeatures;
	}

	public void setProductFeatures(List<String> productFeatures) {
		System.out.println("setProductFeatures");
		this.productFeatures = productFeatures;
	}

	@Override
	public void saveOrUpdateEntity() {
		insuranceObjectService.saveInsuranceBaseInfo(
				this.aggregateRoot.getAggregateId(), this);
	}

	public List<String> getTips() {
		return tips;
	}

	public void setTips(List<String> tips) {
		this.tips = tips;
	}

	public String getSpecRecommend() {
		return specRecommend;
	}

	public void setSpecRecommend(String specRecommend) {
		this.specRecommend = specRecommend;
	}

	@Override
	public String toString() {
		return "InsuranceBaseInfo [safeguardTimeDesc=" + safeguardTimeDesc
				+ ", suitePeopleDesc=" + suitePeopleDesc + ", productFeatures="
				+ productFeatures + "]";
	}

}
