package com.icloud.front.insurance.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.action.BaseController;
import com.icloud.front.user.pojo.UserQueryBean;
import com.icloud.insurance.bo.UserInfoPo;

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
	public ModelAndView userList(UserQueryBean userBean) {
		ModelAndView model = getModelAndView("user/super/user-list");
		if (!ICloudUtils.isNotNull(userBean)) {
			userBean = new UserQueryBean();
		}
		model.addObject("userBean", userBean);
		Pagination<UserInfoPo> pagination = this.userService
				.queryUserList(userBean);
		if (ICloudUtils.isNotNull(pagination)) {
			PageView pageView = PageView.convertPage(pagination);
			model.addObject("pagination", pagination);
			model.addObject("pageView", pageView);
		}
		return model;
	}

	@RequestMapping("/changeUserType")
	@ResponseBody
	public boolean changeUserType(String userId, String opId) {
		logger.info("{} to {}", userId, opId);
		if (ICloudUtils.isNotNull(userId) && ICloudUtils.isNotNull(opId)) {
			int uId = ICloudUtils.parseInt(userId, -1);
			int oId = ICloudUtils.parseInt(opId, -1);
			this.userService.changeUserType(uId, oId);
		}
		return true;
	}

}
