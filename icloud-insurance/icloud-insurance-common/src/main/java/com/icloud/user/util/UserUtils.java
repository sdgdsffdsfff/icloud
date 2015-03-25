package com.icloud.user.util;

import com.icloud.framework.util.ICloudUtils;

public class UserUtils {
	/**
	 * 获得性别
	 * @param initStr
	 * @return
	 */
	public static String getUserSex(String initStr) {
		if (!ICloudUtils.isNotNull(initStr)) {
			initStr = "0";
		}
		initStr = initStr.trim();
		int initSex = 0;
		try {
			initSex = Integer.parseInt(initStr);
		} catch (Exception e) {
			initSex = 0;
		}
		return initSex == 0 ? "男" : "女";
	}
}
