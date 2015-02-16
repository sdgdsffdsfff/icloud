package com.icloud.insurance.config;

import org.slf4j.Logger;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.MailUtil;
import com.icloud.stock.config.ICloudConfig;

public class InsuranceConfig {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(InsuranceConfig.class);
	private static final String ICLOUD_CONSTANTS_FILE_PATH = "properties/insurance-constants.properties";

	public static final int SESSION_TIMEOUT = PropertiesUtil.getPropertyForInt(
			ICLOUD_CONSTANTS_FILE_PATH, "session-timeout");

	public static final String MAIN_URL = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "main_url");

	public static final String FIND_PASSWORD_TITLE = PropertiesUtil
			.getProperty(ICLOUD_CONSTANTS_FILE_PATH, "find_password_tile");
	private static final String MAIL_HOST = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "mailUtil.host");
	private static final String MAIL_USERNAME = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "mailUtil.username");
	private static final String MAIL_PASSWORD = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "mailUtil.password");
	private static final String MAIL_FROM = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "mailUtil.from");

	private static final String findEmailTemplate = PropertiesUtil
			.getContent("properties/findEmailTemplate.txt");

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
		System.out.println(url);
		String content = findEmailTemplate.replace("$url", url);
		sendMailUtil(to, title, content);
	}

	public static void main(String[] args) {
		sendFindPassword("cuijiangning08@163.com", "cuijiangning", "jiangning");
	}
}
