package com.icloud.insurance.domain;

import java.io.File;

import org.junit.Test;

import com.icloud.insurance.service.BaseTest;

public class InsuranceCompanyTest extends BaseTest {
	@Test
	public void saveCompany() {
		File imgFile = new File("D:/cjnpafa/test/insurance/image/pingan.jpg");
		insuranceCompanyAggregateService.saveCompanyAggregate("中国平安",
				this.mediaFileService.getByFile(imgFile));
	}
}
