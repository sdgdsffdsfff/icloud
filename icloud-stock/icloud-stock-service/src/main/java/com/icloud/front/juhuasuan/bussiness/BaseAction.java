package com.icloud.front.juhuasuan.bussiness;

import javax.annotation.Resource;

import com.icloud.stock.service.IJuhuasuanDetailService;
import com.icloud.stock.service.IJuhuasuanSessionService;
import com.icloud.stock.service.IJuhuasuanUrlService;

public class BaseAction {
	@Resource(name = "juhuasuanUrlService")
	protected IJuhuasuanUrlService juhuasuanUrlService;

	@Resource(name = "juhuasuanSessionService")
	protected IJuhuasuanSessionService juhuasuanSessionService;

	@Resource(name = "juhuasuanDetailService")
	protected IJuhuasuanDetailService juhuasuanDetailService;

}
