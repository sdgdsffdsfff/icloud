package com.icloud.front.insurance.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.user.action.BaseController;

@Controller
@RequestMapping("/super")
public class SuperManagerController extends BaseController {
	@RequestMapping("/insuranceCompanyList")
	public ModelAndView insuranceCompanyList() {
		ModelAndView model = getModelAndView("user/super/insurance-company-list");
		return model;
	}

	@RequestMapping("/insuranceCategoryList")
	public ModelAndView insuranceCategoryList() {
		ModelAndView model = getModelAndView("user/super/insurance-category-list");
		return model;
	}

	@RequestMapping("/userList")
	public ModelAndView userList() {
		ModelAndView model = getModelAndView("user/super/user-list");
		return model;
	}

}
