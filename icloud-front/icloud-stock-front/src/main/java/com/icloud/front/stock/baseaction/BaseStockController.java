package com.icloud.front.stock.baseaction;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.front.stock.action.StockController;
import com.icloud.front.stock.bussiness.detail.StockDetailBussiness;
import com.icloud.front.stock.bussiness.menu.StockCommonBussiness;
import com.icloud.front.stock.bussiness.seo.BuuyuuSeoBussiness;
import com.icloud.front.stock.bussiness.view.StockListBussiness;
import com.icloud.stock.search.service.StockNameSearcher;
import com.icloud.user.business.manager.UserAdminBusiness;
import com.icloud.user.business.operation.UserLogOperationBusiness;

public class BaseStockController {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(StockController.class);

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

	protected static Gson gson = new Gson();

	protected ModelAndView getModelAndView(String url) {
		return new ModelAndView(url);
	}

}
