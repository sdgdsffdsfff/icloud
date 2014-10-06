package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.MemberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.WebEnv;
import com.icloud.front.juhuasuan.pojo.JuhuasuanConstants;
import com.icloud.front.juhuasuan.pojo.JuhuasuanConstants.JUHUASUANSTATUS;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.stock.model.JuhuasuanSession;
import com.icloud.stock.model.JuhuasuanUrl;

@Controller
@RequestMapping("/t")
public class JuhuaSuanFrontController extends BaseStockController {

	@RequestMapping("/*")
	public String refer(HttpServletRequest request, HttpSession session) {
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
			String localip = request.getRemoteAddr();
			this.juhuasuanBussiness.processJuhuasuanSession(url, sessionId,
					localip);
			this.juhuasuanBussiness.processJuhuasuanDetail(request, sessionId,
					url);

			if (value == JUHUASUANSTATUS.RUNNING) {
				return "redirect:" + url.getTaobaoUrl();
			}
		}
		return "redirect:" + WebEnv.getBuuyuuUrl();

	}
}
