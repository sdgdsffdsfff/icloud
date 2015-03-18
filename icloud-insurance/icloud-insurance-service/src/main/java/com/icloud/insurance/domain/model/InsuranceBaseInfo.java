package com.icloud.insurance.domain.model;

import com.icloud.insurance.service.InsuranceObjectService;

public class InsuranceBaseInfo extends BaseEntity {
	private String safeguardTimeDesc = null;
	private String suitePeopleDesc = null;
	private InsuranceObjectService insuranceObjectService = null;

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
	public void loadingEntity() {
		
	}

	public String getSafeguardTimeDesc() {
		return safeguardTimeDesc;
	}

	public void setSafeguardTimeDesc(String safeguardTimeDesc) {
		this.safeguardTimeDesc = safeguardTimeDesc;
	}

	public String getSuitePeopleDesc() {
		return suitePeopleDesc;
	}

	public void setSuitePeopleDesc(String suitePeopleDesc) {
		this.suitePeopleDesc = suitePeopleDesc;
	}

	@Override
	public String toString() {
		return "InsuranceBaseInfo [safeguardTimeDesc=" + safeguardTimeDesc
				+ ", suitePeopleDesc=" + suitePeopleDesc + "]";
	}

}
