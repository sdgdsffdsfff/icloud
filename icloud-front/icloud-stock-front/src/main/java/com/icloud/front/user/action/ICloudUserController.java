package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.front.user.utils.ICloudMemberUtils;
import com.icloud.stock.model.User;

@Controller
@RequestMapping("/user")
public class ICloudUserController extends BaseStockController {

	@RequestMapping("/error")
	public ModelAndView doError() {
		ModelAndView model = getErrorModelAndView();
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
			ICloudMemberUtils.addSession(request, response, loginUser, user);
			return "redirect:/stock/stockMenu";
			// return "/user/manager/icloud-user-login";
		} else {
			return "forward:/user/failLoginUser";
		}
	}

	@RequestMapping("/icloudLogout")
	public String icloudLogout(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = getModelAndView("user/manager/icloud-user-logout");
		ICloudMemberUtils.removeSession(request, response);
		return "redirect:/user/icloudLogoutSuccess";
	}

	@RequestMapping("/icloudLogoutSuccess")
	public ModelAndView icloudLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response) {
		return getModelAndView("user/manager/icloud-user-logout");
	}

	@RequestMapping("/myHome")
	public ModelAndView myHome() {
		return getModelAndView("user/myspace/icloud-user-stocklist");
	}

	@RequestMapping("/baseUserInfo")
	public ModelAndView baseUserInfo(String successModifyUserInfo) {
		/**
		 * 取用户信息
		 */
		User user = this.userAdminBusiness
				.getUserByUserId(ICloudUserContextHolder.get());
		if (!ICloudUtils.isNotNull(user)) {
			return getErrorModelAndView();
		}
		ModelAndView modelAndView = getModelAndView("user/myspace/icloud-user-baseinfo");
		modelAndView.addObject("icloudUser", user);
		if (ICloudUtils.isNotNull(successModifyUserInfo)) {
			modelAndView.addObject("successModifyUserInfo",
					successModifyUserInfo);
		}
		return modelAndView;
	}

	@RequestMapping("/modifyBaseInfo")
	public String modifyBaseInfo(RegisterUser registerUser) {
		/**
		 * 取用户信息
		 */
		User user = this.userAdminBusiness
				.getUserByUserId(ICloudUserContextHolder.get());
		if (!ICloudUtils.isNotNull(user)
				|| !ICloudUtils.isNotNull(registerUser)) {
			return ERROR_URL;
		}
		this.userAdminBusiness.modifyBaseInfo(registerUser, user);
		return "redirect:/user/baseUserInfo?successModifyUserInfo=yes";
	}

	@RequestMapping("/modifyPassword")
	public ModelAndView modifyPassword(String result) {
		ModelAndView modelAndView = getModelAndView("user/myspace/icloud-user-modifypassword");
		if (ICloudUtils.isNotNull(result)) {
			if (result.equalsIgnoreCase("success")) {
				modelAndView.addObject("successModifyPw", result);
			} else if (result.equalsIgnoreCase("failure")) {
				modelAndView.addObject("fialModifyPw", result);
			}
		}
		return modelAndView;
	}

	@RequestMapping("/doModifyPassword")
	public String doModifyPassword(
			@RequestParam(required = true) String oldpwd,
			@RequestParam(required = true) String newpwd,
			@RequestParam(required = true) String confirmPwd) {
		/**
		 * 取用户信息
		 */
		User user = this.userAdminBusiness
				.getUserByUserId(ICloudUserContextHolder.get());
		if (!ICloudUtils.isNotNull(user)) {
			return ERROR_URL;
		}
		logger.info("oldpwd:{},newpwd:{},confirmPwd:{}", oldpwd, newpwd,
				confirmPwd);
		if (ICloudUtils.isNotNull(oldpwd) &&ICloudUtils.isNotNull(newpwd)
				&&ICloudUtils.isNotNull(confirmPwd)
				&&newpwd.equalsIgnoreCase(confirmPwd)) {
			if (StringEncoder.encrypt(oldpwd).equalsIgnoreCase(
					user.getUserPassword())) {
				this.userAdminBusiness.updatePassword(user, oldpwd);
				return "redirect:/user/modifyPassword?result=success";
			}
		}
		return "redirect:/user/modifyPassword?result=failure";
	}
}
