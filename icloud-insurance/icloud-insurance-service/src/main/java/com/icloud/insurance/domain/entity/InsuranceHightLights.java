package com.icloud.insurance.domain.entity;

import java.util.List;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;
import com.icloud.insurance.service.InsuranceObjectService;

public class InsuranceHightLights extends InsurnaceBaseDomainEntity {
	private InsuranceObjectService insuranceObjectService;
	private List<String> highlights;

	public InsuranceHightLights(AggregateRoot root,
			InsuranceObjectService insuranceObjectService, boolean lazyLoading) {
		super(root, lazyLoading);
		this.insuranceObjectService = insuranceObjectService;
	}

	public InsuranceHightLights(AggregateRoot root,
			InsuranceObjectService insuranceObjectService) {
		this(root, insuranceObjectService, true);
	}

	@Override
	public void doLoadEntity() {
		this.insuranceObjectService.getInsuranceHightLights(
				this.aggregateRoot.getAggregateId(), this);
	}

	@Override
	public void saveOrUpdateEntity() {
		insuranceObjectService.saveInsuranceHightLights(
				this.aggregateRoot.getAggregateId(), this);
	}

	public List<String> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}

	@Override
	public String toString() {
		return "InsuranceHightLights [highlights=" + highlights + "]";
	}

	@Override
	public void deleteEntity() {
		insuranceObjectService.deleteInsuranceHightLights(
				this.aggregateRoot.getAggregateId(), this);
	}

}
