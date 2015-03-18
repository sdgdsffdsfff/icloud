package com.icloud.insurance.domain.entity;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;
import com.icloud.insurance.service.InsuranceNumberService;
import com.icloud.insurance.util.InsuranceUtil;

public class UnderwritingAge extends
		InsurnaceBaseDomainEntity<InsuranceNumberService> {
	private int startAge = ICloudUtils.DEFAULT_INT_VALUE;
	private int endAge = ICloudUtils.DEFAULT_INT_VALUE;

	public UnderwritingAge(AggregateRoot root,
			InsuranceNumberService insuranceNumberService, boolean lazyLoading) {
		super(root, insuranceNumberService, lazyLoading);
	}

	public UnderwritingAge(AggregateRoot root,
			InsuranceNumberService insuranceNumberService) {
		this(root, insuranceNumberService, true);
	}

	@Override
	public void doLoadEntity() {
		// TODO Auto-generated method stub
		this.baseService.getUnderwritingAge(
				this.aggregateRoot.getAggregateId(), this);
	}

	@Override
	public void saveOrUpdateEntity() {
		this.baseService.saveUnderwritingAge(
				this.aggregateRoot.getAggregateId(), this);
	}

	public int getStartAge() {
		this.checkLoad();
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		this.checkLoad();
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	public String getUnderwritingAgeForString() {
		return InsuranceUtil.convertTimeToAge(startAge, endAge);
	}

	@Override
	public String toString() {
		return "UnderwritingAge [startAge=" + startAge + ", endAge=" + endAge
				+ "]";
	}

}
