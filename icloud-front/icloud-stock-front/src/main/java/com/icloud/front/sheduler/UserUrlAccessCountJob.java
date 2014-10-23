package com.icloud.front.sheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月23日 下午5:05:13
 */
@Component("userUrlAccessCountJob")
public class UserUrlAccessCountJob {
	@Scheduled(cron = "0/3 * * * * ?")
	public void dailyUpdateUserUrlAccessCountTask() {
		System.out.println("任务进行中。。。");
	}
}
