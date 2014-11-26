package com.icloud.stock.marketing.email;

import java.io.IOException;

import com.icloud.stock.marketing.resouce.EmailTaskManager;

public class EmailSenderTask {

	public EmailSenderTask() {

	}

	public void send() throws IOException {
		String srcPath = "/data/mywork/task/emailtask1/os.txt";
		String destPath = "/data/mywork/task/emailtask1/work.txt";
		EmailSenderImpl sender = new EmailSenderImpl();
		EmailTaskManager manager = new EmailTaskManager(10, 10000, srcPath,
				destPath, sender);
		manager.run();
	}

	public static void main(String[] args) throws IOException {
		EmailSenderTask sender = new EmailSenderTask();
		sender.send();
	}
}
