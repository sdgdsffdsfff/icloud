package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JuhuasuanSearchBean;
import com.icloud.front.utils.ModelAndViewUtils;
import com.icloud.user.bussiness.po.UserInfoPo;

@Controller
@RequestMapping("/userpt")
public class ICloudMemberController extends BaseStockController {
	@RequestMapping("/dayStat")
	public ModelAndView myFollowerList(JuhuasuanSearchBean searchBean) {
		ModelAndView model = getModelAndView("user/taobao/member/icloud-member-dayStat-view");
		
		return model;
	}
}
