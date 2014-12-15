package com.icloud.stock.marketing.resouce;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icloud.framework.performance.runnable.ThreadStat;
import com.icloud.framework.util.MailUtil;
import com.icloud.stock.marketing.content.EmailContentTemplate;
import com.icloud.stock.marketing.pojo.FromEmail;
import com.icloud.stock.marketing.pojo.Proxy;
import com.icloud.stock.marketing.task.TestContentTemplate;

public class EmailSendUtil {
	private static EmailList emailList = new EmailList();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThreadStat.class);

	private static boolean sendMailUtil(FromEmail fromEmail, String to,
			String title, String html) {
		// if (to.indexOf("163.com") != -1)
		// return false;
		// fromEmail.setEmail("qq10@tmalllo.me");
		// fromEmail.setHost("mail.tmalllo.me");
		// // fromEmail.setPassword("@123Gabc12345");
		// fromEmail.setPassword("Abc12345");
		// fromEmail.setUsename("qq10@tmalllo.me");
		LOGGER.info(fromEmail.getEmail());
		MailUtil mailUtil = new MailUtil();
		mailUtil.setHost(fromEmail.getHost());
		mailUtil.setUsername(fromEmail.getUsename());
		mailUtil.setPassword(fromEmail.getPassword());
		mailUtil.setFrom(fromEmail.getEmail());
		mailUtil.setTo(to);

		mailUtil.setSubject(title);
		mailUtil.setContent(html);
		mailUtil.setContentType("text/html;charset=utf-8");
		// boolean result = mailUtil.sendMail();
		Proxy proxy = emailList.getProxy();
		boolean result = mailUtil.sendMailByProxy(proxy.getIp(),
				proxy.getPort());
		return result;
	}

	public static boolean sendMailUtil(String to, String title, String html) {
		try {
			boolean isOk = sendMailUtil(emailList.getEmail(), to, title, html);
			if (isOk) {
				LOGGER.info("ok in {}", to);
			} else {
				LOGGER.error("error in {}", to);
			}
			return isOk;
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error("error in {}", to);
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		EmailContentTemplate contentTemplate = new TestContentTemplate();
		String channel = "1";
		String ditch = "oschina";
		// String email = "cuijiangning08@163.com";
		// String email = "qq002@tmalllo.me";
		String email = "363255417@qq.com";
		// String email = "ball1949@21cn.com";
		sendMailUtil(email, contentTemplate.getTilte(email, channel, ditch),
				contentTemplate.getContent(email, channel, ditch));
		// sendMailUtil(email, "test", "test");
	}
}
