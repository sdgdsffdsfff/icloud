package com.icloud.insurance.domain;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.icloud.insurance.domain.model.InsuranceAggregate;
import com.icloud.insurance.domain.service.InsuranceAggregateService;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.BaseTest;

public class InsuranceAggregateTest extends BaseTest {
	@Autowired
	private InsuranceAggregateService insuranceAggregateService;

	@Test
	public void saveInsuranceProduct() {
		InsuranceProduct product = new InsuranceProduct();
		product.setCrateTime(new Date());
		product.setInsuranceName("中民无忧综合意外基本计划-计划一");
		product.setInsuranceCompany("中国人寿");
		product.setSimpleDescription("一款适用少儿、工人、白领人士、中老年阶层的精简、实用的综合意外保障计划。性价比极高的意外险产品。");
		product.setSafeguardTime(365);
		product.setCrateTime(new Date());
		product.setLastUpdateTime(new Date());
		product.setLastUpdateUserId(1);
		product.setLastUpdateUserName("cuijiangning");
		product.setInsuranceStatus(0);
		insuranceAggregateService.saveInsuranceProduct(product);
	}

	@Test
	public void InsuranceAggregateLoadingTest() {
		InsuranceAggregate insuranceAggregate = insuranceAggregateService
				.getInsuranceAggregateById(1);
		System.out.println(insuranceAggregate);
	}

}
