package com.icloud.user.service;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.stock.model.User;

public interface IUserService extends ISqlBaseService<User> {

	User getUserByUserName(String userName);

	User getUserByEmail(String email);

	User getUserByTelphone(String telphone);

	User getUser(String email, String password);

	void resetPassword(User user);

	void updatePassword(User user, String encrypt);

}
