package com.icloud.insurance.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.icloud.insurance.domain.aggregate.InsuranceAggregate;
import com.icloud.insurance.domain.aggregate.InsuranceCompanyAggregate;
import com.icloud.insurance.domain.entity.InsuranceBaseInfo;
import com.icloud.insurance.domain.entity.InsuranceHightLights;
import com.icloud.insurance.domain.entity.UnderwritingAge;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.InsuranceCategoryEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemStatusEnum;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.BaseTest;

public class InsuranceAggregateTest extends BaseTest {

	@Test
	public void delete() {
		insuranceAggregateService.deleteAggregate(1);
	}

	@Test
	public void saveInsuranceProduct() {
		InsuranceCompanyAggregate company = this.insuranceCompanyAggregateService
				.getInsuranceCompanyAggregate(14);
		InsuranceProduct product = new InsuranceProduct();
		product.setInsuranceName("中民无忧综合意外基本计划-计划一");
		product.setInsuranceCompany(company.getCompanyName());
		product.setInsuranceCompanyId(company.getCompanyId());
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
		InsuranceAggregateSaveBaseInfo(product.getId());
	}

	@Test
	public void InsuranceAggregateSaveUnderWritingAge() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggregate();
		UnderwritingAge underwritingAge = insuranceAggregate
				.getUnderwritingAge();
		underwritingAge.setEndAge(309);
		underwritingAge.setStartAge(1);
		underwritingAge.saveOrUpdateEntity();
		System.out.println(insuranceAggregate);
	}


	public void InsuranceAggregateSaveBaseInfo(int productId) {
		InsuranceAggregate insuranceAggregate = getInsuranceAggregate(
				productId, true);
		InsuranceBaseInfo insuranceBaseInfo = insuranceAggregate
				.getInsuranceBaseInfo();
		insuranceBaseInfo.setSafeguardTimeDesc("自3天后生效");
		insuranceBaseInfo.setSuitePeopleDesc("一些中等收入家庭");
		List<String> features = new ArrayList<String>();
		features.add("意外伤害、意外医疗精简实用搭配，一款适用少儿、工人、白领人士、中老年阶层的精简、实用、性价比极高的综合意外保障计划！");
		features.add("该产品保障1-4类职业人士！意外医疗100元免赔，100%比例报销！");
		features.add("网络投保，足不出户即可货比三家；365天全年无休，在线提供咨询和保全变更服务。");
		insuranceBaseInfo.setProductFeatures(features);
		List<String> tips = new ArrayList<String>();
		tips.add("本计划由中国人寿保险股份有限公司承保，目前该公司在北京、上海、天津、重庆、河北、陕西、内蒙古、山西、辽宁、吉林、黑龙江、江苏、安徽、浙江、福建、江西、山东、湖北、湖南、河南、广东、广西、海南、四川、云南、贵州、甘肃、青海、宁夏、西藏、新疆、大连、青岛、宁波、厦门、深圳有分支机构，客户从中民保险网购买，后续变更与理赔事务均可由中民保险网协助您办理。");
		tips.add("对于父母为其未成年子女投保的人身保险，在被保险人成年之前，各保险合同约定的被保险人死亡给付的保险金额总和、被保险人死亡时各保险公司实际给付的保险金总和均不得超过人民币10万元。");
		tips.add("本计划最多可购买1份，多买无效。");
		tips.add("外籍人士可投保，但需在中国境内拥有居留证或长期居住权，或持有中华人民共和国政府部门签发的工作签证；且本产品医疗费用和住院津贴不承保在台湾、香港、澳门地区或中国境外发生的治疗。");
		tips.add("本计划不承保高风险运动导致的意外伤害，高风险运动包括但不限于潜水、跳伞、攀岩、探险、蹦极、驾驶滑翔机、武术比赛、摔跤、特技表演、赛马、赛车等。");
		tips.add("本计划中的医疗相关保障限定为中华人民共和国境内合法经营的二级以上（含二级）医院或保险公司认可的其他医疗机构，除了北京平谷区所有医院。请注意：北京平谷区所有医院的就医均不给予理赔，建议您去往其他区域二级以上公立医院就医。");
		tips.add("本计划为网络销售产品，网上投保、网上支付、网上即时核保并出具保险凭证，您可拨打中国人寿保险公司电话02095519查询、验真。");
		insuranceBaseInfo.setTips(tips);
		insuranceBaseInfo.setSpecRecommend("全市场意外险最高性价比，刺穿意外险市场底价！");
		insuranceBaseInfo.saveOrUpdateEntity();
		System.out.println(insuranceAggregate);
	}

	@Test
	public void InsuranceAggregateSaveHightLights() {
		InsuranceAggregate insuranceAggregate = getInsuranceAggregate();
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
		InsuranceAggregateLoadingTest(false);
	}

	@Test
	public void InsuranceAggregateTest() {
		InsuranceAggregateLoadingTest(true);
	}

	public void InsuranceAggregateLoadingTest(boolean loading) {
		InsuranceAggregate insuranceAggregate = getInsuranceAggregate(1,
				loading);
		System.out.println(insuranceAggregate);
		UnderwritingAge underwritingAge = insuranceAggregate
				.getUnderwritingAge();
		System.out.println("-----------||||" + underwritingAge.getStartAge());
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
		getInsuranceAggregate();
	}

	public InsuranceAggregate getInsuranceAggregate(int id, boolean flag) {
		InsuranceAggregate insuranceAggregate = insuranceAggregateService
				.getInsuranceAggregateById(id, flag);
		return insuranceAggregate;
	}

	public InsuranceAggregate getInsuranceAggregate() {
		return getInsuranceAggregate(5, false);
	}

	public InsuranceAggregate getInsuranceAggregateNoLoading() {
		return getInsuranceAggregate(5, true);
	}

}
