package com.icloud.front.juhuasuan.bussiness;

import org.springframework.stereotype.Service;

import com.icloud.stock.model.JuhuasuanUrl;

@Service("juhuasuanBussiness")
public class JuhuasuanBussiness extends BaseAction {

	public JuhuasuanUrl saveJuhuasuanUrl(JuhuasuanUrl url) {
		this.juhuasuanUrlService.save(url);
		return url;
	}
}
