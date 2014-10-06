package com.icloud.juhuasuan.util;

public class UrlCodeUtil {
	private static final int INIT_CODE = 0;

	public static String generateJuhuasuanCode(int id) {
		id = id + INIT_CODE;
		StringBuffer sb = new StringBuffer();
		while (id > 0) {
			int i = id % 26;
			char c = (char) ('A' + i);
			sb.append(c);
			id = id / 26;
		}

		String code = sb.reverse().toString();
		if (code.length() == 0)
			return "AA";
		if (code.length() == 1)
			return "A" + code;
		return code;

	}

	public static void main(String[] args) {
		System.out.println(generateJuhuasuanCode(5));
	}
}
