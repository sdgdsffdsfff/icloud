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
import com.icloud.stock.model.User;

@Controller
@RequestMapping("/t")
public class JuhuaSuanFrontController extends BaseStockController {

	@RequestMapping("/*")
	public String refer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return redirectToUrl(request, response, session);
	}

}
