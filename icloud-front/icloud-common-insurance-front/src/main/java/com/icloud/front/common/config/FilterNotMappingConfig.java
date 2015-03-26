package com.icloud.front.common.config;

import org.slf4j.Logger;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;

public class FilterNotMappingConfig {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(FilterNotMappingConfig.class);

	private static final String FILTERNOTMAPPING_FILE_PATH = "properties/filter-not-mapping.properties";
	private static final String FILTERNOTMAPPING_URL = PropertiesUtil
			.getProperty(FILTERNOTMAPPING_FILE_PATH, "filter-not-mapping");

	public static boolean isInFilterUrl(String url) {
		if (ICloudUtils.isNotNull(url)) {
			if (url.indexOf(FILTERNOTMAPPING_URL) != -1)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String url = "http://www.buuyuu.com/resousrces/afa.jsp";
		System.out.println(isInFilterUrl(url));
	}
}
