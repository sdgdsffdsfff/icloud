package com.icloud.framework.util;

import java.text.NumberFormat;
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

	public static int parseInt(String str, int defaultValue) {
		int id = defaultValue;
		try {
			id = Integer.parseInt(str);
		} catch (Exception e) {
			id = defaultValue;
		}
		return id;
	}

	public static int parseInt(String str) {
		return parseInt(str, -1);
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

	/**
	 * 显示数据
	 */
	public static String getDigitalString(double d, int n) {
		if (n <= 0) {
			n = 2;
		}
		NumberFormat nFormat = NumberFormat.getNumberInstance();
		nFormat.setGroupingUsed(false);
		nFormat.setMaximumFractionDigits(n);
		if (d > 10000 * 10000) {
			d = d / (10000 * 10000);
			return nFormat.format(d) + "亿";
		} else if (d > 10000) {
			d = d / 10000;
			return nFormat.format(d) + "万";
		} else {
			return nFormat.format(d);
		}
	}

	/**
	 * 显示数据
	 */
	public static String getDigitalString(double d) {
		NumberFormat nFormat = NumberFormat.getNumberInstance();
		nFormat.setGroupingUsed(false);
		nFormat.setMaximumFractionDigits(2);
		return nFormat.format(d);
	}

	public static void main(String[] args) {
		double d = 178333;
		System.out.println(getDigitalString(d));
	}

	public static boolean isSame(String name, String name2) {
		name = trim(name);
		name2 = trim(name2);
		if (name == null && name2 == null)
			return true;
		if (name == null)
			return false;
		if (name2 == null)
			return false;
		if (name.equalsIgnoreCase(name2))
			return true;
		return false;
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
}
