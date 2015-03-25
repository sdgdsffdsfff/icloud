package com.icloud.front.insurance.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.user.action.BaseController;

@Controller
@RequestMapping("/proxy")
public class ProxyManagerController extends BaseController {

	@RequestMapping("/addInsuranceView")
	public ModelAndView addInsurance() {
		ModelAndView model = getModelAndView("user/proxy/add-Insurance-view");
		return model;
	}

	@RequestMapping("/insuranceList")
	public ModelAndView insuranceList() {
		ModelAndView model = getModelAndView("user/proxy/insurance-list-view");
		return model;
	}

}
