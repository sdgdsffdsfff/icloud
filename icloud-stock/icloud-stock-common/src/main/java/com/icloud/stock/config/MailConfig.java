package com.icloud.stock.config;

import org.slf4j.Logger;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.MailUtil;

public class MailConfig {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(MailConfig.class);

	private static final String MAIL_FILE_PATH = "properties/mail-constants.properties";
	private static final String MAIL_HOST = PropertiesUtil.getProperty(
			MAIL_FILE_PATH, "mailUtil.host");
	private static final String MAIL_USERNAME = PropertiesUtil.getProperty(
			MAIL_FILE_PATH, "mailUtil.username");
	private static final String MAIL_PASSWORD = PropertiesUtil.getProperty(
			MAIL_FILE_PATH, "mailUtil.password");
	private static final String MAIL_FROM = PropertiesUtil.getProperty(
			MAIL_FILE_PATH, "mailUtil.from");

	private static void sendMailUtil(String to, String title, String html) {
		MailUtil mailUtil = new MailUtil();
		mailUtil.setHost(MAIL_HOST);
		mailUtil.setUsername(MAIL_USERNAME);
		mailUtil.setPassword(MAIL_PASSWORD);
		mailUtil.setFrom(MAIL_FROM);
		mailUtil.setTo(to);
		mailUtil.setSubject(title);
		mailUtil.setContent(html);
		mailUtil.setContentType("text/html;charset=utf-8");
		boolean result = mailUtil.sendMail();
		logger.info("send mail: {}, title={}, result={}", to, title, result);
		logger.info("html: {}", html);
	}

	public static void sendFindPassword(String to, String userName,
			String password) {
		String title = ICloudConfig.FIND_PASSWORD_TITLE;
		String url = ICloudConfig.MAIN_URL
				+ "/userManager/dofindPassWordStep3?userName=" + userName
				+ "&token=" + password;
		String html = "<html><head><title>找回密码</title></head>"
				+ "<body><center><a href=\"http://www.buuyuu.com\">必有网www.buuyuu.com</a></center>"
				+ "<p>请点击以下链接进行重置密码，谢谢您的关注"
				+ "<p><a href=\""
				+ url
				+ "\">"
				+ url
				+ "</a>"
				+ "<p>多谢您的支持，<a href=\"http://www.buuyuu.com\">必有网</a>的发展离不开您的关注。"
				+ "</body></html>";

		sendMailUtil(to, title, html);
	}

	public static void main(String[] args) {
		sendFindPassword("cuijiangning08@163.com", "cuijiangning", "jiangning");
	}
}
