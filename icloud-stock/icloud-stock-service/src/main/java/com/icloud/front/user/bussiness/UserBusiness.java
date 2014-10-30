package com.icloud.front.user.bussiness;

import javax.annotation.Resource;

import org.slf4j.Logger;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.user.service.ISessionService;
import com.icloud.user.service.IUserService;

public class UserBusiness {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(UserBusiness.class);
	
	@Resource(name = "sessionService")
	protected ISessionService sessionService;

	@Resource(name = "userService")
	protected IUserService userService;

}
