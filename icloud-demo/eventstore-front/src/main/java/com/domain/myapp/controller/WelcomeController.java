package com.domain.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("/index")
	public String helloWorld() {
		/**
		 * 入口
		 */
		return "forward:/customer/listCustomers";
	}
}
