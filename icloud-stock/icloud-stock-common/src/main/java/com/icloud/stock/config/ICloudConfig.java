package com.icloud.stock.config;

import com.icloud.framework.config.PropertiesUtil;

public class ICloudConfig {
	private static final String ICLOUD_CONSTANTS_FILE_PATH = "properties/icloud-constants.properties";

	public static final int SESSION_TIMEOUT = PropertiesUtil.getPropertyForInt(
			ICLOUD_CONSTANTS_FILE_PATH, "session-timeout");

	public static final String MAIN_URL = PropertiesUtil.getProperty(
			ICLOUD_CONSTANTS_FILE_PATH, "main_url");

	public static final String FIND_PASSWORD_TITLE = PropertiesUtil
			.getProperty(ICLOUD_CONSTANTS_FILE_PATH, "find_password_tile");

}
