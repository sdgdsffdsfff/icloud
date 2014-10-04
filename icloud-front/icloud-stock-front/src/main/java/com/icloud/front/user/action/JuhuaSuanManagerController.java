package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/usertb")
public class JuhuaSuanManagerController extends BaseStockController {

	@RequestMapping("/tbList")
	public ModelAndView baseUserInfo(String successModifyUserInfo) {
		return this.getErrorModelAndView();
//		ModelAndView modelAndView = getModelAndView("user/taobao/tblist");
////		modelAndView.addObject("icloudUser", user);
//		if (ICloudUtils.isNotNull(successModifyUserInfo)) {
//			modelAndView.addObject("successModifyUserInfo",
//					successModifyUserInfo);
//		}
//		return modelAndView;
	}
}
