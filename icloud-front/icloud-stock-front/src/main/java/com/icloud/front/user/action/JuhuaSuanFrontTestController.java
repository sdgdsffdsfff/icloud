package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.WebEnv;
import com.icloud.front.juhuasuan.pojo.JuhuasuanConstants;
import com.icloud.front.juhuasuan.pojo.JuhuasuanConstants.JUHUASUANSTATUS;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.stock.model.JuhuasuanUrl;
import com.sun.xml.internal.messaging.saaj.soap.Envelope;

@Controller
@RequestMapping("/r")
public class JuhuaSuanFrontTestController extends BaseStockController {

	@RequestMapping("/test")
	public String refer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "redirect:http://www.baidu.com";
	}
}
