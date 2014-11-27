package com.icloud.stock.marketing.resouce;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.icloud.framework.util.MailUtil;
import com.icloud.stock.marketing.content.EmailContentTemplate;
import com.icloud.stock.marketing.pojo.FromEmail;
import com.icloud.stock.marketing.task.TestContentTemplate;

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
		try {
			return sendMailUtil(emailList.getEmail(), to, title, html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		EmailContentTemplate contentTemplate = new TestContentTemplate();
		String channel = "1";
		String ditch = "oschina";
		// String email = "cuijiangning08@163.com";
		String email = "363255417@qq.com";
		sendMailUtil(email, contentTemplate.getTilte(email, channel, ditch),
				contentTemplate.getContent(email, channel, ditch));
	}
}
