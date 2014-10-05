package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JuhuasuanUrlBean;
import com.icloud.stock.model.JuhuasuanUrl;
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

	@RequestMapping("/doAddJuhuasuanUrl")
	public ModelAndView doAddJuhuasuanUrl(JuhuasuanUrlBean bean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/juhuasuanUrlView");
		if (ICloudUtils.isNotNull(bean)) {
			JuhuasuanUrl urlBean = JuhuasuanUrlBean
					.convertJuhuasuanUrlBean(bean);
			urlBean = this.juhuasuanBussiness.saveJuhuasuanUrl(urlBean);
			modelAndView.addObject("urlBean", urlBean);
		}
		return modelAndView;
	}

	@RequestMapping("/juhuasuanUrlView")
	public ModelAndView juhuasuanUrlView(String code) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/juhuasuanUrlView");
		if (ICloudUtils.isNotNull(code)) {
			// JuhuasuanUrl urlBean = JuhuasuanUrlBean
			// .convertJuhuasuanUrlBean(bean);
			// urlBean = this.juhuasuanBussiness.saveJuhuasuanUrl(urlBean);
			// modelAndView.addObject("urlBean", urlBean);
		}
		return modelAndView;
	}
}
