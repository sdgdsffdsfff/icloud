package com.icloud.user.business.manager;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.stock.model.User;
import com.icloud.user.business.UserBusiness;

@Service("userAdminBusiness")
public class UserAdminBusiness extends UserBusiness {

	public User addUser(String userName, String password) {
		if (ICloudUtils.isNotNull(userName) && ICloudUtils.isNotNull(password)) {
			password = StringEncoder.encrypt(password);// 加密

		}
		return null;
	}

	public User getUserByUserName(String userName) {
		if (ICloudUtils.isNotNull(userName)) {
			return this.userService.getUserByUserName(userName);
		}
		return null;
	}

	public User getUserByEmail(String email) {
		if (ICloudUtils.isNotNull(email)) {
			return this.userService.getUserByEmail(email);
		}
		return null;
	}

	public User getUserByTelphone(String telphone) {
		if (ICloudUtils.isNotNull(telphone)) {
			return this.userService.getUserByTelphone(telphone);
		}
		return null;
	}

}
