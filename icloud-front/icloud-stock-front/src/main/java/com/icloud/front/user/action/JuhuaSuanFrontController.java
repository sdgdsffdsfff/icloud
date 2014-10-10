package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.WebEnv;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANSTATUS;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.stock.model.JuhuasuanUrl;

@Controller
@RequestMapping("/t")
public class JuhuaSuanFrontController extends BaseStockController {

	@RequestMapping("/*")
	public String refer(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		String uri = request.getRequestURI();
		String code = "";
		if (uri.lastIndexOf("/") != -1) {
			code = uri.substring(uri.lastIndexOf("/") + 1);
		}
		JuhuasuanUrl url = this.juhuasuanBussiness.getJuhuasuanUrlByCode(code);
		if (ICloudUtils.isNotNull(url)) {
			/**
			 * 填充数据
			 */
			JUHUASUANSTATUS value = JuhuasuanConstants.JUHUASUANSTATUS
					.value(url.getStatus());

			String sessionId = session.getId();
			// String localip = request.getRemoteAddr();
			String localip = request.getHeader("X-Real-IP");
			this.juhuasuanBussiness.processJuhuasuanSession(url, sessionId,
					localip);
			this.juhuasuanBussiness.processJuhuasuanDetail(request, sessionId,
					url);

			if (value == JUHUASUANSTATUS.RUNNING) {
				ModelAndView modelAndView = new ModelAndView(
						"user/taobao/redirect/taobao-redirect");
				String originUrl = url.getOriginUrl();
				if (!ICloudUtils.isNotNull(originUrl)) {
					if (url.getType().equalsIgnoreCase(
							JuhuasuanConstants.JUHUASUANTYPE.SINGLE.getId())) {
						originUrl = WebEnv.get("href.path.taobao.single");
					} else {
						originUrl = WebEnv.get("href.path.taobao.ju");
					}
				} else {
					originUrl = originUrl.trim();
				}
				modelAndView.addObject("preUrl", url.getTaobaoUrl().trim());
				modelAndView.addObject("lastUrl", originUrl);
//				return modelAndView;
				return "redirect:" + url.getTaobaoUrl().trim();
			}
		}
		 return "redirect:" + WebEnv.getBuuyuuUrl();
		// ModelAndView modelAndView = new ModelAndView(
		// "user/taobao/redirect/taobao-redirect");
		// modelAndView.addObject("preUrl", WebEnv.getBuuyuuUrl());
		// modelAndView.addObject("lastUrl", WebEnv.getBuuyuuUrl());
		// return modelAndView;
	}
}
