package com.icloud.insurance.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.icloud.insurance.domain.aggregate.InsuranceAggregate;
import com.icloud.insurance.domain.entity.InsuranceBaseInfo;
import com.icloud.insurance.domain.entity.InsuranceHightLights;
import com.icloud.insurance.domain.entity.UnderwritingAge;
import com.icloud.insurance.domain.service.InsuranceAggregateService;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.InsuranceCategoryEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemStatusEnum;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.BaseTest;
import com.icloud.insurance.service.InsuranceAttributeService;

public class InsuranceAggregateTest extends BaseTest {
	@Autowired
	private InsuranceAggregateService insuranceAggregateService;
	@Autowired
	private InsuranceAttributeService insuranceAttributeService;

	@Test
	public void saveInsuranceProduct() {
		InsuranceProduct product = new InsuranceProduct();
		product.setInsuranceName("中民无忧综合意外基本计划-计划一");
		product.setInsuranceCompany("中国人寿");
		product.setSimpleDescription("一款适用少儿、工人、白领人士、中老年阶层的精简、实用的综合意外保障计划。性价比极高的意外险产品。");
		product.setSafeguardTime(365);
		product.setCreateTime(new Date());
		product.setLastUpdateTime(new Date());
		product.setLastUpdateUserId(1);
		product.setLastUpdateUserName("cuijiangning");
		product.setInsuranceStatus(SystemStatusEnum.OK.getStatus());
		product.setInsuranceCategoryId(insuranceAttributeService
				.getInsuranceAttributeIdFromUUID(InsuranceCategoryEnum.ACCIDENT_INSURANCE
						.getUuid()));
		insuranceAggregateService.saveInsuranceProduct(product);
	}

	@Test
	public void InsuranceAggregateSaveUnderWritingAge() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggreate();
		UnderwritingAge underwritingAge = insuranceAggregate
				.getUnderwritingAge();
		underwritingAge.setEndAge(309);
		underwritingAge.setStartAge(1);
		underwritingAge.saveOrUpdateEntity();
		System.out.println(insuranceAggregate);
	}

	@Test
	public void InsuranceAggregateSaveBaseInfo() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggreate();
		InsuranceBaseInfo insuranceBaseInfo = insuranceAggregate
				.getInsuranceBaseInfo();
		insuranceBaseInfo.setSafeguardTimeDesc("自3天后生效");
		insuranceBaseInfo.setSuitePeopleDesc("一些中等收入家庭");
		insuranceBaseInfo.saveOrUpdateEntity();
		System.out.println(insuranceAggregate);
	}

	@Test
	public void InsuranceAggregateSaveHightLights() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggreate();
		InsuranceHightLights insuranceHightLights = insuranceAggregate
				.getInsuranceHightLights();
		insuranceAggregate.setInsuranceHightLights(insuranceHightLights);
		List<String> list = new ArrayList<String>();
		list.add("满期返还型");
		list.add("专保癌症");
		insuranceHightLights.setHighlights(list);
		insuranceHightLights.saveOrUpdateEntity();
	}

	@Test
	public void InsuranceAggregateLoadingTest() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggreate();
		System.out.println(insuranceAggregate);
		UnderwritingAge underwritingAge = insuranceAggregate
				.getUnderwritingAge();
		System.out.println(underwritingAge);
		// System.out.println(underwritingAge.getUnderwritingAgeForString());
		// InsuranceBaseInfo insuranceBaseInfo = insuranceAggregate
		// .getInsuranceBaseInfo();
		// System.out.println(insuranceBaseInfo);
		// InsuranceHightLights insuranceHightLights = insuranceAggregate
		// .getInsuranceHightLights();
		// System.out.println(insuranceHightLights);
	}

	@Test
	public void testGetInsuranceAggreate() {
		getInsuranceAggreate();
	}

	public InsuranceAggregate getInsuranceAggreate(int id, boolean flag) {
		InsuranceAggregate insuranceAggregate = insuranceAggregateService
				.getInsuranceAggregateById(id, flag);
		return insuranceAggregate;
	}

	public InsuranceAggregate getInsuranceAggreate() {
		return getInsuranceAggreate(1, false);
	}

}
