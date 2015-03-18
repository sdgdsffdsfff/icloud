package com.icloud.insurance.domain.entity;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;
import com.icloud.insurance.service.InsuranceObjectService;

public class InsuranceBaseInfo extends
		InsurnaceBaseDomainEntity<InsuranceObjectService> {
	private String safeguardTimeDesc;
	private String suitePeopleDesc;

	public InsuranceBaseInfo(AggregateRoot root, InsuranceObjectService t) {
		super(root, t);
	}

	public InsuranceBaseInfo(AggregateRoot root, InsuranceObjectService t,
			boolean lazyLoading) {
		super(root, t, lazyLoading);
	}

	@Override
	public void doLoadEntity() {
		this.baseService.getInsuranceBaseInfo(
				this.aggregateRoot.getAggregateId(), this);
	}

	public String getSafeguardTimeDesc() {
		checkLoad();
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

	@Override
	public void saveOrUpdateEntity() {
		this.baseService.saveInsuranceBaseInfo(
				this.aggregateRoot.getAggregateId(), this);
	}

	@Override
	public String toString() {
		return "InsuranceBaseInfo [safeguardTimeDesc=" + safeguardTimeDesc
				+ ", suitePeopleDesc=" + suitePeopleDesc + "]";
	}

}
