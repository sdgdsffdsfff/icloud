package com.icloud.front.stock.baseaction;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.Paper.bussiness.PaperBussiness;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.common.utils.WebEnv;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanConstantBussiness;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANSTATUS;
import com.icloud.front.marketing.bussiness.MarketingBusiness;
import com.icloud.front.stock.bussiness.BuuyuuSeoBussiness;
import com.icloud.front.stock.bussiness.StockCommonBussiness;
import com.icloud.front.stock.bussiness.StockDetailBussiness;
import com.icloud.front.stock.bussiness.StockListBussiness;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.front.user.bussiness.UserLogOperationBusiness;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.model.JuhuasuanUrl;
import com.icloud.stock.model.User;
import com.icloud.stock.search.service.StockNameSearcher;

public class BaseStockController {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(BaseStockController.class);
	protected static final String SECURE_SEED = "Abc124456";

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
	@Resource(name = "juhuasuanConstantBussiness")
	protected JuhuasuanConstantBussiness juhuasuanConstantBussiness;

	@Resource(name = "marketingBusiness")
	protected MarketingBusiness marketingBusiness;

	@Resource(name = "paperBussiness")
	protected PaperBussiness paperBussiness;

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

	protected UserInfo getUserInfo() {
		return ICloudUserContextHolder.get();
	}

	protected int getUserId() {
		return getUser().getId();
	}

	protected String redirectToUrl(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String uri = request.getRequestURI();
		String code = "";
		if (uri.lastIndexOf("/") != -1) {
			code = uri.substring(uri.lastIndexOf("/") + 1);
		}
		JuhuasuanUrl url = this.juhuasuanBussiness.getJuhuasuanUrlByCode(code);
		if (ICloudUtils.isNotNull(url)) {
			/**
			 * 检查权限
			 */
			User user = this.userAdminBusiness.getUser(url.getUserId());
			if (ICloudUtils.isNotNull(user) && user.getOpen() == 0) {
				return "redirect:" + WebEnv.getBuuyuuUrl();
			}
			/**
			 * 填充数据
			 */
			JUHUASUANSTATUS value = JuhuasuanConstants.JUHUASUANSTATUS
					.value(url.getStatus());

			String sessionId = session.getId();
			String localip = request.getHeader("X-Real-IP");
			if (ICloudUtils.isNotNull(localip)) {
				localip = request.getRemoteAddr();
			}
			this.juhuasuanBussiness.processJuhuasuanSession(url, sessionId,
					localip);
			this.juhuasuanBussiness.processJuhuasuanDetail(request, sessionId,
					url);

			if (value == JUHUASUANSTATUS.RUNNING) {
				String taobaoUrl = url.getTaobaoUrl().trim();
				/**
				 * 如果是超级链接
				 */
				if (ICloudUtils.isNotNull(url.getMoreFlag())
						&& url.getMoreFlag() == 1) {
					List<String> list = this.juhuasuanBussiness.getMoreUrl(url);
					if (!ICloudUtils.isEmpty(list)) {
						list.add(taobaoUrl);
						/**
						 * 获得随机数
						 */
						int index = ICloudUtils.getRandom(list.size());
						taobaoUrl = list.get(index);
					}
				}
				// ModelAndView modelAndView = new ModelAndView(
				// "user/taobao/redirect/taobao-redirect");
				// String originUrl = url.getOriginUrl();
				// if (!ICloudUtils.isNotNull(originUrl)) {
				// if (url.getType().equalsIgnoreCase(
				// JuhuasuanConstants.JUHUASUANTYPE.SINGLE.getId())) {
				// originUrl = WebEnv.get("href.path.taobao.single");
				// } else {
				// originUrl = WebEnv.get("href.path.taobao.ju");
				// }
				// } else {
				// originUrl = originUrl.trim();
				// }
				// modelAndView.addObject("preUrl", url.getTaobaoUrl().trim());
				// modelAndView.addObject("lastUrl", originUrl);
				// return "redirect:" + url.getTaobaoUrl().trim();
				return "redirect:" + taobaoUrl;
			}
		}
		return "redirect:" + WebEnv.getBuuyuuUrl();
	}
}
