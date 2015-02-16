package com.icloud.front.user.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.insurance.model.User;
import com.icloud.insurance.service.UserService;

public class BaseController {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(BaseController.class);
	protected static final String SECURE_SEED = "Abc124456";

	protected static final String ERROR_URL = "redirect:/user/error";

	@Resource(name = "userService")
	protected UserService userService;

	protected static Gson gson = new Gson();

	protected ModelAndView getModelAndView(String url) {
		return new ModelAndView(url);
	}

	protected ModelAndView getErrorModelAndView(String tip) {
		if (!ICloudUtils.isNotNull(tip)) {
			tip = "Oh,God!操作出错了";
		}
		ModelAndView model = getModelAndView("icloud/icloud-error");
		model.addObject("errorTip", tip);
		return model;
	}

	protected ModelAndView getErrorModelAndView() {
		return getErrorModelAndView("Oh,God!操作出错了");
	}

	protected User getUser() {
		User user = this.userService.getUserByUserInfo(ICloudUserContextHolder
				.get());
		return user;
	}

	protected UserInfo getUserInfo() {
		return ICloudUserContextHolder.get();
	}

	protected int getUserId() {
		return getUser().getId();
	}

}
