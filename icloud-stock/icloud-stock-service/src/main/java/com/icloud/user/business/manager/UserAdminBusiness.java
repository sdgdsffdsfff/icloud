package com.icloud.user.business.manager;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.stock.model.User;
import com.icloud.user.business.UserBusiness;
import com.icloud.user.util.UserUtils;

@Service("userAdminBusiness")
public class UserAdminBusiness extends UserBusiness {

	public User addUser(String userName, String password) {
		if (ICloudUtils.isNotNull(userName) && ICloudUtils.isNotNull(password)) {
			password = StringEncoder.encrypt(password);// 加密

		}
		return null;
	}

	public User getUser(LoginUser loginUser) {
		if (ICloudUtils.isNotNull(loginUser)
				&& ICloudUtils.isNotNull(loginUser.getEmail())
				&& ICloudUtils.isNotNull(loginUser.getPassword())) {
			String password = StringEncoder.encrypt(loginUser.getPassword());// 加密
			return this.userService.getUser(loginUser.getEmail(), password);
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

	/**
	 * 增加一个
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(RegisterUser registerUser, String coming) {
		if (ICloudUtils.isNotNull(registerUser)) {
			/**
			 * 校对用户名，电话号码，邮箱
			 */
			if (ICloudUtils.isNotNull(registerUser.getUsername())
					&& ICloudUtils.isNotNull(registerUser.getEmail())
					&& ICloudUtils.isNotNull(registerUser.getReadContract())
					&& ICloudUtils.isNotNull(registerUser.getTelphone())
					&& ICloudUtils.isNotNull(registerUser.getUsersex())
					&& ICloudUtils
							.isNotNull(registerUser.getConfirm_password())
					&& ICloudUtils.isNotNull(registerUser.getPassword())
					&& registerUser.getConfirm_password().equalsIgnoreCase(
							registerUser.getPassword())) {

				if (!ICloudUtils.isNotNull(getUserByUserName(registerUser
						.getUsername()))
						&& !ICloudUtils.isNotNull(getUserByEmail(registerUser
								.getEmail()))
						&& !ICloudUtils
								.isNotNull(getUserByTelphone(registerUser
										.getTelphone()))) {

					User user = new User();
					user.setCreateTime(new Date());
					user.setLastUpdateTime(user.getCreateTime());
					user.setUserComing(coming);
					user.setUserEmail(registerUser.getEmail());
					user.setUserName(registerUser.getUsername());
					user.setUserPassword(StringEncoder.encrypt(registerUser
							.getPassword()));// 对用户密码加密
					user.setUserSex(UserUtils.getUserSex(registerUser
							.getUsersex()));
					user.setUserTel(registerUser.getTelphone());
					return this.userService.save(user);
				}
			}
		}
		return null;
	}

}
