package com.icloud.insurance.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InsuranceServiceTest extends BaseTest{
	@Autowired
	private InsuranceNumberService insuranceNumberService;

	@Test
	public void testGet(){
		insuranceNumberService.getById(1);
	}
}
