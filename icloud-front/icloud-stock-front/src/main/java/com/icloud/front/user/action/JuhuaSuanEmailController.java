package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/f")
public class JuhuaSuanEmailController extends BaseStockController {

	@RequestMapping("/*")
	public String refer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String email,
			String channel, String ditch) {
		if (ICloudUtils.isNotNull(email) && ICloudUtils.isNotNull(channel)
				&& email.indexOf("@") != -1) {
			this.marketingBusiness.update(email, channel, ditch);
		}
		return redirectToUrl(request, response, session);
	}
}
