package com.icloud.juhuasuan.util;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.util.ICloudUtils;

public class ReferUtil {
	private static List<String> terms = new ArrayList<String>();

	static {
		String path = "properties/taobaoterm.properties";
		String str = PropertiesUtil.getProperty(path, "taobao.terms");
		String[] tStr = str.split(",");
		for (String t : tStr)
			terms.add(t);
	}

	/**
	 * 含有以下字段的就可以
	 * 
	 * @param refer
	 * @return
	 */
	public static int isValid(String refer) {
		if (ICloudUtils.isNotNull(refer)) {
			System.out.println(terms);
			for (String t : terms) {
				if (refer.contains(t))
					return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(isValid("http://www.taobao.com"));
	}
}
