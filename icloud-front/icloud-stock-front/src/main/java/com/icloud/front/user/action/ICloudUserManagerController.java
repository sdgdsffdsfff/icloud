package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/userManager")
public class ICloudUserManagerController extends BaseStockController {

	@RequestMapping("/registerView")
	public ModelAndView registerView() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-register-view");
		return model;
	}

	@RequestMapping("/doRegisterUser")
	public ModelAndView doRegisterUser() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-register-success");
		return model;
	}

	@RequestMapping("/dofindPassWordStep1")
	public ModelAndView dofindPassWordStep1() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-findpwd-step01");
		return model;
	}

	@RequestMapping("/dofindPassWordStep2")
	public ModelAndView dofindPassWordStep2() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-findpwd-step02");
		return model;
	}

	@RequestMapping("/dofindPassWordStep3")
	public ModelAndView dofindPassWordStep3() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-findpwd-step03");
		return model;
	}

	@RequestMapping("/dofindPassWordStep4")
	public ModelAndView dofindPassWordStep4() {
		ModelAndView model = new ModelAndView(
				"user/manager/icloud-user-findpwd-step04");
		return model;
	}
}
