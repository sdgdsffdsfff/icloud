package com.icloud.front.juhuasuan.bussiness;

import javax.annotation.Resource;

import org.slf4j.Logger;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.stock.service.IJuhuasuanDetailService;
import com.icloud.stock.service.IJuhuasuanSessionService;
import com.icloud.stock.service.IJuhuasuanUrlService;
import com.icloud.stock.service.IUserUrlAccessCountService;
import com.icloud.user.service.IUserService;

public class BaseAction {
	protected Logger logger = RequestIdentityLogger.getLogger(this.getClass());
	@Resource(name = "juhuasuanUrlService")
	protected IJuhuasuanUrlService juhuasuanUrlService;

	@Resource(name = "juhuasuanSessionService")
	protected IJuhuasuanSessionService juhuasuanSessionService;

	@Resource(name = "juhuasuanDetailService")
	protected IJuhuasuanDetailService juhuasuanDetailService;

	@Resource(name = "userService")
	protected IUserService userService;

	@Resource(name = "userUrlAccessCountService")
	protected IUserUrlAccessCountService userUrlAccessCountService;

}
