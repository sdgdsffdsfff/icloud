package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.insurance.config.InsuranceConfig;
import com.icloud.insurance.model.User;
import com.icloud.user.dict.UserConstants;

@Controller
@RequestMapping("/userManager")
public class ICloudUserManagerController extends BaseController {

	@RequestMapping("/registerView")
	public ModelAndView registerView() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-register-view");
		return model;
	}

	@RequestMapping("/validateUserName")
	@ResponseBody
	public boolean validateUserName(
			@RequestParam(required = true) String username) {
		logger.info("{}", username);
		if (ICloudUtils.isNotNull(this.userService.getUserByUserName(username))) {
			return false;
		}
		return true;
	}

	@RequestMapping("/validateEmail")
	@ResponseBody
	public boolean validateEmail(@RequestParam(required = true) String email,
			String requiredTrue) {
		logger.info("{}", email);
		if (ICloudUtils.isNotNull(this.userService.getUserByEmail(email))) {
			// return false;
			return ICloudUtils.isNotNull(requiredTrue) ? true : false;
		}
		// return true;
		return ICloudUtils.isNotNull(requiredTrue) ? false : true;
	}

	@RequestMapping("/validateTelphone")
	@ResponseBody
	public boolean validateTelphone(
			@RequestParam(required = true) String telphone) {
		logger.info("{}", telphone);
		if (ICloudUtils.isNotNull(this.userService.getUserByTelphone(telphone))) {
			return false;
		}
		return true;
	}

	@RequestMapping("/doRegisterUser")
	public String doRegisterUser(RegisterUser registerUser) {
		if (!ICloudUtils.isNotNull(registerUser)) {
			return ERROR_URL;
		}
		logger.info("start to register, {}", registerUser.toString());
		User user = this.userService.addUser(registerUser,
				UserConstants.COMMING.COM_COMMING.getName(), null);
		if (ICloudUtils.isNotNull(user)) {
			logger.info("success to register, {}", registerUser.toString());
			return "redirect:/userManager/registersuccess";
		} else {
			logger.info("fail to register, {}", registerUser.toString());
			return ERROR_URL;
		}
	}

	@RequestMapping("/registersuccess")
	public ModelAndView registersuccess() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-register-success");
		return model;
	}

	@RequestMapping("/dofindPassWordStep1")
	public ModelAndView dofindPassWordStep1(LoginUser user) {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step01");
		return model;
	}

	@RequestMapping("/dofindPassWordStep2")
	public String dofindPassWordStep2(LoginUser loginUser) {
		String email = loginUser.getEmail();
		// 查找这个用户
		logger.info("email={}", email);
		// 发送邮件给该邮箱
		User user = this.userService.getUserByEmail(email);

		// 重置密码和发送邮件
		if (ICloudUtils.isNotNull(user)) {
			/**
			 * 充值密码
			 */
			// userService.resetPassword(user);
			String token = userService.getToken(user);
			InsuranceConfig.sendFindPassword(email, user.getUserName(), token);
		}
		return "redirect:/userManager/dofindPassWordStep2success?email="
				+ ICloudUtils.getEmailSite(email);
	}

	@RequestMapping("/dofindPassWordStep2success")
	public ModelAndView dofindPassWordStep2success(String email) {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step02");
		model.addObject("email", email);
		return model;
	}

	@RequestMapping("/dofindPassWordStep3")
	public ModelAndView dofindPassWordStep3(
			@RequestParam(required = true) String userName,
			@RequestParam(required = true) String token) {
		User user = this.userService.getUserByUserName(userName);
		if (ICloudUtils.isNotNull(user) && userService.checkToken(user, token)) {
			ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step03");
			model.addObject("userName", userName);
			model.addObject("token", token);
			return model;
		} else {
			ModelAndView model = getErrorModelAndView("您申请的找回密码功能出现错误，有以下原因：1. 过期;2.非法操作");
			return model;
		}
	}

	@RequestMapping("/doFindAndUpdatePwd")
	public String dofindPassWordStep2(RegisterUser registerUser) {
		if (ICloudUtils.isNotNull(registerUser)
				&& ICloudUtils.isNotNull(registerUser.getPassword())
				&& ICloudUtils.isNotNull(registerUser.getConfirm_password())
				&& registerUser.getPassword().equalsIgnoreCase(
						registerUser.getConfirm_password())) {
			User user = this.userService.getUserByUserName(registerUser
					.getUsername());
			if (ICloudUtils.isNotNull(user)) {
				if (ICloudUtils.isNotNull(user.getUserPassword())
						&& userService.checkToken(user, registerUser.getToken())){
					this.userService.updatePassword(user,
							registerUser.getPassword());
					return "redirect:/userManager/dofindPassWordStep4";
				}
			}

		}
		return ERROR_URL;
	}

	@RequestMapping("/dofindPassWordStep4")
	public ModelAndView dofindPassWordStep4() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step04");
		return model;
	}
}
