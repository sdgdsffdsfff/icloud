package com.icloud.front.user.action;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.StockBean;
import com.icloud.front.stock.pojo.StockCompleteResult;

@Controller
@RequestMapping("/userManager")
public class ICloudUserManagerController extends BaseStockController {

	@RequestMapping("/registerView")
	public ModelAndView registerView() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-register-view");
		return model;
	}

	@RequestMapping("/validateUserName")
	@ResponseBody
	public boolean validateUserName(@RequestParam(required = true) String username) {
		logger.info("{}", username);
		return false;
	}

	@RequestMapping("/doRegisterUser")
	public ModelAndView doRegisterUser() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-register-success");
		return model;
	}

	@RequestMapping("/dofindPassWordStep1")
	public ModelAndView dofindPassWordStep1() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step01");
		return model;
	}

	@RequestMapping("/dofindPassWordStep2")
	public ModelAndView dofindPassWordStep2() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step02");
		return model;
	}

	@RequestMapping("/dofindPassWordStep3")
	public ModelAndView dofindPassWordStep3() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step03");
		return model;
	}

	@RequestMapping("/dofindPassWordStep4")
	public ModelAndView dofindPassWordStep4() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-findpwd-step04");
		return model;
	}

	@RequestMapping("/icloudLogin")
	public ModelAndView icloudLogin() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-login");
		return model;
	}

	@RequestMapping("/icloudLogout")
	public ModelAndView icloudLogout() {
		ModelAndView model = getModelAndView("user/manager/icloud-user-logout");
		return model;
	}
}
