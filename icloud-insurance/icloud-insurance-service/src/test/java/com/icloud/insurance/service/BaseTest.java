package com.icloud.insurance.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/icloud-insurance-service-ctx-min.xml" })
public class BaseTest {

	// @Autowired
	// private CustomerMapper customerMapper;
	//
	// @Test
	// public void readAllCustomer(){
	// Customer customer = customerMapper.selectByPrimaryKey(2);
	// }
}
