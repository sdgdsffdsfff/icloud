package com.icloud.insurance.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.insurance.dao.UserDao;
import com.icloud.insurance.domain.service.InsuranceAggregateService;
import com.icloud.insurance.domain.service.InsuranceCompanyAggregateService;
import com.icloud.insurance.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/icloud-insurance-service-ctx-min.xml" })
public class BaseTest {

	@Autowired
	protected UserDao userDao;

	@Autowired
	protected InsuranceAggregateService insuranceAggregateService;
	@Autowired
	protected InsuranceAttributeService insuranceAttributeService;
	@Autowired
	protected InsuranceNumberService insuranceNumberService;
	@Autowired
	protected MediaFileService mediaFileService;

	@Autowired
	protected InsuranceCompanyAggregateService insuranceCompanyAggregateService;

	@Test
	public void readAllCustomer() {
		List<User> list = userDao.findAll();
		for (User user : list) {
			System.out.println(user.getChinaName());
		}
	}

}
