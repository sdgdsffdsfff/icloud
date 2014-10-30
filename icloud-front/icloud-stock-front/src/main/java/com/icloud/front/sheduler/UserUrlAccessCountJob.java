package com.icloud.front.sheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icloud.front.juhuasuan.bussiness.JuhuasuanStatBusiness;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月23日 下午5:05:13
 */
@Component("userUrlAccessCountJob")
public class UserUrlAccessCountJob {
	@Autowired
	private JuhuasuanStatBusiness juhuasuanStatBusiness;
	@Scheduled(cron = "0 10 0 * * ?")
	public void dailyUpdateUserUrlAccessCountTask() {
		this.juhuasuanStatBusiness.updateUserUrlAccessCountDaily();
	}
}
