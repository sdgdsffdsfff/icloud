package com.icloud.framework.util;

import java.util.Collection;
import java.util.List;

public class ICloudUtils {

	public static <T> boolean isEmpty(Collection<T> collection) {
		if (collection == null)
			return true;
		if (collection.size() == 0)
			return true;
		return false;
	}

	public static boolean isNotNull(String str) {
		if (str == null || str.trim().length() == 0)
			return false;
		return true;
	}

	public static int parseInt(String str) {
		int id = -1;
		try {
			id = Integer.parseInt(str);
		} catch (Exception e) {
			id = -1;
		}
		return id;
	}

	public static boolean isNotNull(Object object) {
		return object == null ? false : true;
	}

	public static boolean isNotNull(Object[] objects) {
		if (objects == null || objects.length == 0)
			return false;
		return true;

	}

	/**
	 * 从字符串中获得内容
	 *
	 */
	public static String getSubStringFromContent(String content,
			String startIndexStr, String endIndexStr) {
		if (ICloudUtils.isNotNull(content)
				&& ICloudUtils.isNotNull(startIndexStr)
				&& ICloudUtils.isNotNull(endIndexStr)) {
			int startIndex = content.indexOf(startIndexStr);
			if (startIndex != -1) {
				content = content
						.substring(startIndex + startIndexStr.length());
				int endIndex = content.indexOf(endIndexStr);
				if (endIndex != -1) {
					return content.substring(0, endIndex).trim();
				}
			}
		}
		return null;
	}

	/**
	 * 获得第n个元素
	 */
	public static <T> T getElement(List<T> list, int n) {
		if (!ICloudUtils.isEmpty(list) && list.size() > n) {
			return list.get(n);
		}
		return null;
	}

	public static <T> T getFirstElement(List<T> list) {
		return getElement(list, 0);
	}
}
