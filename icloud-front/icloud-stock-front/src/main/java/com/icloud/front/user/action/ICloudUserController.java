package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/user")
public class ICloudUserController extends BaseStockController {

	@RequestMapping("/registerView")
	public ModelAndView stockMenu() {
		ModelAndView model = new ModelAndView("user/icloud-user-register-view");
		return model;
	}


}
