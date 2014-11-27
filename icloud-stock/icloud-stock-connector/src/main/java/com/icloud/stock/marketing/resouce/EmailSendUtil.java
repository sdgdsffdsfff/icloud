package com.icloud.stock.marketing.resouce;

import com.icloud.framework.util.MailUtil;
import com.icloud.stock.marketing.pojo.FromEmail;

public class EmailSendUtil {
	private static EmailList emailList = new EmailList();

	private static boolean sendMailUtil(FromEmail fromEmail, String to,
			String title, String html) {
		MailUtil mailUtil = new MailUtil();
		mailUtil.setHost(fromEmail.getHost());
		mailUtil.setUsername(fromEmail.getUsename());
		mailUtil.setPassword(fromEmail.getPassword());
		mailUtil.setFrom(fromEmail.getEmail());
		mailUtil.setTo(to);
		mailUtil.setSubject(title);
		mailUtil.setContent(html);
		mailUtil.setContentType("text/html;charset=utf-8");
		boolean result = mailUtil.sendMail();
		return result;
	}

	public static boolean sendMailUtil(String to, String title, String html) {
		return sendMailUtil(emailList.getEmail(), to, title, html);
	}
}
