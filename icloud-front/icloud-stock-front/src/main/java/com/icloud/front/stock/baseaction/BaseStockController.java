package com.icloud.front.stock.baseaction;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.front.stock.bussiness.BuuyuuSeoBussiness;
import com.icloud.front.stock.bussiness.StockCommonBussiness;
import com.icloud.front.stock.bussiness.StockDetailBussiness;
import com.icloud.front.stock.bussiness.StockListBussiness;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.front.user.bussiness.UserLogOperationBusiness;
import com.icloud.stock.model.User;
import com.icloud.stock.search.service.StockNameSearcher;

public class BaseStockController {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(BaseStockController.class);

	protected static final String ERROR_URL = "redirect:/user/error";

	@Resource(name = "stockCommonBussiness")
	protected StockCommonBussiness stockCommonBussiness;
	@Resource(name = "stockDetailBussiness")
	protected StockDetailBussiness stockDetailBussiness;
	@Resource(name = "stockListBussiness")
	protected StockListBussiness stockListBussiness;
	@Resource(name = "buuyuuSeoBussiness")
	protected BuuyuuSeoBussiness buuyuuSeoBussiness;
	@Resource(name = "stockNameSearcher")
	protected StockNameSearcher stockNameSearcher;
	@Resource(name = "userAdminBusiness")
	protected UserAdminBusiness userAdminBusiness;
	@Resource(name = "userLogOperationBusiness")
	protected UserLogOperationBusiness userLogOperationBusiness;
	@Resource(name = "juhuasuanBussiness")
	protected JuhuasuanBussiness juhuasuanBussiness;

	protected static Gson gson = new Gson();

	protected ModelAndView getModelAndView(String url) {
		return new ModelAndView(url);
	}

	protected ModelAndView getErrorModelAndView(String tip) {
		if (!ICloudUtils.isNotNull(tip)) {
			tip = "Oh,God!操作出错了";
		}
		ModelAndView model = getModelAndView("icloud/icloud-error");
		model.addObject("errorTip", tip);
		return model;
	}

	protected ModelAndView getErrorModelAndView() {
		return getErrorModelAndView("Oh,God!操作出错了");
	}

	protected User getUser() {
		User user = this.userAdminBusiness
				.getUserByUserInfo(ICloudUserContextHolder.get());
		return user;
	}

	protected int getUserId() {
		return getUser().getId();
	}
}
