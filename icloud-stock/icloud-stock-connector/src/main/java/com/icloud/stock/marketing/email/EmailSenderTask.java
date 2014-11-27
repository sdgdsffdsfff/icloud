package com.icloud.stock.marketing.email;

import java.io.IOException;

import com.icloud.stock.marketing.content.EmailContentTemplate;
import com.icloud.stock.marketing.resouce.EmailSenderImpl;
import com.icloud.stock.marketing.resouce.EmailTaskManager;
import com.icloud.stock.marketing.task.TestContentTemplate;

public class EmailSenderTask {

	public EmailSenderTask() {

	}

	public void send(EmailContentTemplate template, String channel,
			String ditch, String srcPath, String destPath, int number, int sum)
			throws IOException {
		EmailSenderImpl sender = new EmailSenderImpl(template, channel, ditch);
		EmailTaskManager manager = new EmailTaskManager(10, 10000, srcPath,
				destPath, sender);
		manager.run();
	}

	public void testSend() throws IOException {
		String srcPath = "/data/mywork/task/emailtask1/os.txt";
		String destPath = "/data/mywork/task/emailtask1/work.txt";
		EmailContentTemplate contentTemplate = new TestContentTemplate();
		String channel = "1";
		String ditch = "oschina";
		int number = 10;
		int sum = 10000;
		send(contentTemplate, channel, ditch, srcPath, destPath, number, sum);
	}

	public static void main(String[] args) throws IOException {
		EmailSenderTask sender = new EmailSenderTask();
		sender.testSend();
	}
}
