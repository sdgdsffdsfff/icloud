package com.icloud.user.business;

import javax.annotation.Resource;

import com.icloud.user.service.ISessionService;
import com.icloud.user.service.IUserService;

public class UserBusiness {
	@Resource(name = "sessionService")
	protected ISessionService sessionService;

	@Resource(name = "userService")
	protected IUserService userService;

}
