package com.icloud.insurance.domain.valueobject;

import com.icloud.insurance.service.InsuranceAttributeService;

public class InsuranceAggregateValueObject {
	public static int UNDER_WRITING_AGE_KEY;
	public static int SAFEGUARDTIME_KEY;
	public static int SUITEPEOPLE_KEY;
	public static int PRODUCTHIGHLIGHTS_KEY;
	public static int PORDUCTFEATURES_KEY;
	public static int PRODUCTTIPS_KEY;
	public static int PRODUCT_RECOMMEND_KEY;

	public static void init(InsuranceAttributeService insuranceAttributeService) {
		UNDER_WRITING_AGE_KEY = insuranceAttributeService
				.getUnderWritingAgeKey();
		SAFEGUARDTIME_KEY = insuranceAttributeService.getSafeGuardTimeKey();
		SUITEPEOPLE_KEY = insuranceAttributeService.getSuitePeopleKey();
		PRODUCTHIGHLIGHTS_KEY = insuranceAttributeService
				.getProductHightLightsKey();
		PORDUCTFEATURES_KEY = insuranceAttributeService.getProductFeatureKey();
		PRODUCTTIPS_KEY = insuranceAttributeService.getProductTipsKey();
		PRODUCT_RECOMMEND_KEY = insuranceAttributeService
				.getProductRecommendKey();
	}
}
