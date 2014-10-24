package com.icloud.user.service.test;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.stock.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/icloud-stock-service-ctx-min.xml" })
public class UserTest {

	@Resource(name = "userAdminBusiness")
	private UserAdminBusiness userAdminBusiness;

	@Test
	public void updateTest() {
		User user = userAdminBusiness.getUserByEmail("cuijiangning08@163.com");
		userAdminBusiness.resetPassword(user);
	}

	@Test
	public void getAllUsers() {
		Pagination<User> pagination = this.userAdminBusiness
				.getAllUsers(0, 100);
		Collection<User> data = pagination.getData();
		for (User user : data) {
			System.out.println(user.toString());
		}
	}
}
