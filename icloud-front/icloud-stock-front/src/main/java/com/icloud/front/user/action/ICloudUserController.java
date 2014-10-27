package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JuhuasuanSearchBean;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.front.user.utils.ICloudMemberUtils;
import com.icloud.front.utils.ModelAndViewUtils;
import com.icloud.stock.model.User;
import com.icloud.user.bussiness.po.UserInfoPo;
import com.icloud.user.dict.UserConstants;

@Controller
@RequestMapping("/user")
public class ICloudUserController extends BaseStockController {

	@RequestMapping("/error")
	public ModelAndView doError() {
		ModelAndView model = getErrorModelAndView();
		return model;
	}

	// @RequestMapping("/icloudLogin")
	// public ModelAndView icloudLogin() {
	// ModelAndView model = getModelAndView("user/manager/icloud-user-login");
	// return model;
	// }

	@RequestMapping("/icloudLogout")
	public String icloudLogout(HttpServletRequest request,
			HttpServletResponse response) {
		// ModelAndView model =
		// getModelAndView("user/manager/icloud-user-logout");
		ICloudMemberUtils.removeSession(request, response);
		return "redirect:/facade/icloudLogoutSuccess";
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
				.getUserByUserInfo(ICloudUserContextHolder.get());
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
				.getUserByUserInfo(ICloudUserContextHolder.get());
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
				.getUserByUserInfo(ICloudUserContextHolder.get());
		if (!ICloudUtils.isNotNull(user)) {
			return ERROR_URL;
		}
		logger.info("oldpwd:{},newpwd:{},confirmPwd:{}", oldpwd, newpwd,
				confirmPwd);
		if (ICloudUtils.isNotNull(oldpwd) && ICloudUtils.isNotNull(newpwd)
				&& ICloudUtils.isNotNull(confirmPwd)
				&& newpwd.equalsIgnoreCase(confirmPwd)) {
			if (StringEncoder.encrypt(oldpwd).equalsIgnoreCase(
					user.getUserPassword())) {
				this.userAdminBusiness.updatePassword(user, oldpwd);
				return "redirect:/user/modifyPassword?result=success";
			}
		}
		return "redirect:/user/modifyPassword?result=failure";
	}

	@RequestMapping("/registerView")
	public ModelAndView registerView() {
		ModelAndView model = getModelAndView("user/myspace/icloud-user-register-view");
		return model;
	}

	@RequestMapping("/doRegisterUser")
	public String doRegisterUser(RegisterUser registerUser) {
		if (!ICloudUtils.isNotNull(registerUser)) {
			return ERROR_URL;
		}
		User ownUser = this.userAdminBusiness
				.getUserByUserInfo(ICloudUserContextHolder.get());
		logger.info("start to register, {}", registerUser.toString());
		User user = this.userAdminBusiness.addUser(registerUser,
				UserConstants.COMMING.COM_COMMING.getName(), ownUser);
		if (ICloudUtils.isNotNull(user)) {
			logger.info("success to register, {}", registerUser.toString());
			return "redirect:/user/registersuccess";
		} else {
			logger.info("fail to register, {}", registerUser.toString());
			return ERROR_URL;
		}
	}

	@RequestMapping("/registersuccess")
	public ModelAndView registersuccess() {
		ModelAndView model = getModelAndView("user/myspace/icloud-user-register-view");
		model.addObject("successModifyUserInfo", true);
		return model;
	}

	@RequestMapping("/myFollowerList")
	public ModelAndView myFollowerList(JuhuasuanSearchBean searchBean) {
		ModelAndView model = getModelAndView("user/myspace/icloud-user-follower-list-view");
		Pagination<UserInfoPo> pagination = this.userAdminBusiness
				.getUsersByUser(getUser(), searchBean.getPageNo(),
						searchBean.getLimit());
		ModelAndViewUtils.addPageView(model, pagination);
		return model;
	}
}
