package com.icloud.stock.marketing.resouce;

import com.icloud.framework.performance.runnable.TaskRunnable;
import com.icloud.framework.performance.runnable.ThreadStat;
import com.icloud.framework.util.ICloudUtils;

public class EmailRunnable extends TaskRunnable {
	private EmailResource resouce;
	private EmailSender sender;

	public EmailRunnable(String threadName, ThreadStat threadStat) {
		super(threadName, threadStat);
	}

	public EmailRunnable(String threadName, ThreadStat threadStat,
			EmailResource resouce, EmailSender sender) {
		super(threadName, threadStat);
		this.resouce = resouce;
		this.sender = sender;
	}

	public String doRun() {
		String line = resouce.next();
		if (ICloudUtils.isNotNull(line)) {
			// System.out.println(line);
			// 发送邮件
			boolean flag = sender.send(line);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag)
				return "成功";
			else
				return "失败";
		}
		return "为空";
	}
}
