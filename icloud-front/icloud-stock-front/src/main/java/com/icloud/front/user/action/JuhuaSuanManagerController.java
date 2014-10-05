package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.stock.model.User;

@Controller
@RequestMapping("/usertb")
public class JuhuaSuanManagerController extends BaseStockController {

	@RequestMapping("/tbList")
	public ModelAndView baseUserInfo(String successModifyUserInfo) {
		ModelAndView modelAndView = getModelAndView("user/taobao/tblist");
		User user = this.userAdminBusiness
				.getUserByUserInfo(ICloudUserContextHolder.get());
		modelAndView.addObject("icloudUser", user);
		if (ICloudUtils.isNotNull(successModifyUserInfo)) {
			modelAndView.addObject("successModifyUserInfo",
					successModifyUserInfo);
		}
		return modelAndView;
	}

	@RequestMapping("/addJuhuasuanUrlView")
	public ModelAndView addJuhuasuanUrlView() {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/addJuhuasuanUrlView");
		return modelAndView;
	}
}
