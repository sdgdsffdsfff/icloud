package com.icloud.insurance.domain.valueobject;

import com.icloud.insurance.service.InsuranceAttributeService;

public class InsuranceAggregateValueObject {
	public static int UNDER_WRITING_AGE_KEY;

	public static void init(InsuranceAttributeService insuranceAttributeService) {
		UNDER_WRITING_AGE_KEY = insuranceAttributeService
				.getUnderWritingAgeKey();
	}
}
