package com.icloud.front.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/r")
public class JuhuaSuanFrontTestController extends BaseStockController {

	@RequestMapping("/test")
	public String refer(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		return "redirect:http://www.baidu.com";
	}
}
