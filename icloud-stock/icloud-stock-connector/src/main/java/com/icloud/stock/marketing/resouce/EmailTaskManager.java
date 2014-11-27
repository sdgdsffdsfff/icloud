package com.icloud.stock.marketing.resouce;

import java.io.IOException;
import java.util.List;

import com.icloud.stock.marketing.runnable.TaskManager;
import com.icloud.stock.marketing.runnable.TaskRunnable;
import com.icloud.stock.marketing.runnable.ThreadStat;

public class EmailTaskManager extends TaskManager {

	private EmailResource resouce;
	private EmailSender sender;

	public EmailTaskManager(int thread_limit, int work_sum) {
		super(thread_limit, work_sum);
	}

	// public EmailTaskManager(int thread_limit, int work_sum,
	// EmailResource resouce, EmailSender sender) {
	// super(thread_limit, work_sum);
	// this.resouce = resouce;
	// this.sender = sender;
	// }

	public EmailTaskManager(int thread_limit, int work_sum, String srcPath,
			String destPath, EmailSender sender) throws IOException {
		super(thread_limit, work_sum);
		EmailFileOperation operation = new EmailFileOperation();
		List<String> lines = operation.readerLines(srcPath, destPath, work_sum);
		EmailResource resouce = new EmailResource(lines);
		this.resouce = resouce;
		this.sender = sender;
	}

	@Override
	public TaskRunnable getPerformanceRunnable(String threadName,
			ThreadStat threadStat) {
		return new EmailRunnable(threadName, threadStat, resouce, sender);
	}

}
