package com.icloud.front.common.interceptor;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.UserInfo;

public class DenyUtils {
	public static String[] normalUrl = { "super", "proxy" };
	public static String[] proxyUrl = { "super" };

	public static boolean isIllegal(UserInfo info, String url) {
		if (!(ICloudUtils.isNotNull(url) && url.length() > 2 && url
				.startsWith("/"))) {
			return true;
		}
		url = url.substring(1);
		if (ICloudUtils.isNotNull(info)) {
			if (info.isProxy()) {
				for (String s : proxyUrl) {
					if (url.startsWith(s)) {
						return false;
					}
				}
				return true;
			}
			if (info.isNormal()) {
				for (String s : normalUrl) {
					if (url.startsWith(s)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;

	}
}
