package com.icloud.stock.marketing.resouce;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.marketing.runnable.TaskRunnable;
import com.icloud.stock.marketing.runnable.ThreadStat;

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
			if (flag)
				return 1 + "";
		}
		return 0 + "";
	}
}
