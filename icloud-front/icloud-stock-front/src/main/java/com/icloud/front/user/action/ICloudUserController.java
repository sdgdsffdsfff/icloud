package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.utils.ICloudMemberUtils;
import com.icloud.stock.model.User;

@Controller
@RequestMapping("/user")
public class ICloudUserController extends BaseStockController {

	@RequestMapping("/error")
	public ModelAndView doError() {
		ModelAndView model = getModelAndView("icloud/icloud-error");
		return model;
	}

	@RequestMapping("/icloudLogin")
	public ModelAndView icloudLogin() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-login");
		return model;
	}

	@RequestMapping("/failLoginUser")
	public ModelAndView failLoginUser(LoginUser loginUser) {
		if (ICloudUtils.isNotNull(loginUser)) {
			logger.info("{}", loginUser.toString());
		}
		ModelAndView model = getModelAndView("/user/manager/icloud-user-login");
		model.addObject("user", loginUser);
		model.addObject("failTip", "邮箱/密码不匹配");
		return model;
	}

	@RequestMapping("/doLoginUser")
	public String doLoginUser(HttpServletRequest request,
			HttpServletResponse response, LoginUser loginUser) {
		if (ICloudUtils.isNotNull(loginUser)) {
			logger.info("{}", loginUser.toString());
		}
		User user = this.userAdminBusiness.getUser(loginUser);
		if (ICloudUtils.isNotNull(user)) {
			/**
			 * 加入cookie,并且下次自动登录
			 */
			ICloudMemberUtils.addSession(request, response,
					loginUser, user);
			return "redirect:/stock/stockMenu";
			// return "/user/manager/icloud-user-login";
		} else {
			return "forward:/user/failLoginUser";
		}
	}

	@RequestMapping("/icloudLogout")
	public ModelAndView icloudLogout() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-logout");
		return model;
	}
}
