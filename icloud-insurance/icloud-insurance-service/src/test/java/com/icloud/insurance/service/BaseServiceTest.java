package com.icloud.insurance.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceTest extends BaseTest {
	@Autowired
	private UserService userService;

	@Test
	public void addUsers() {
	}

	@Test
	public void readAOP() {
		userService.getUserByUserName("nihao");
	}
}
