package com.icloud.juhuasuan.util;

import java.security.MessageDigest;
import java.util.Random;

public class UrlCodeUtil {
	private static final int INIT_CODE = 0;
	private static Random random = new Random();

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

		if (code.length() == 0) {
			code = "AA";
		} else if (code.length() == 1) {
			code = "A" + code;
		}
		// return "AA";
		// if (code.length() == 1)
		// return "A" + code;
		// // return code;
		return generate(code);
	}

	public static String generate(String url) {
		String[] aResult = shortUrl(url);// 将产生4组6位字符串
		int j = random.nextInt(4);// 产成4以内随机数
		return aResult[j];
	}

	public static String[] shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "test";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"

		};
		// 对传入网址进行 MD5 加密
		String hex = md5ByHex(key + url);

		String[] resUrl = new String[4];
		for (int i = 0; i < 4; i++) {

			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,
			// 如果不用long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}

	/**
	 * MD5加密(32位大写)
	 * 
	 * @param src
	 * @return
	 */
	public static String md5ByHex(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = src.getBytes();
			md.reset();
			md.update(b);
			byte[] hash = md.digest();
			String hs = "";
			String stmp = "";
			for (int i = 0; i < hash.length; i++) {
				stmp = Integer.toHexString(hash[i] & 0xFF);
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else {
					hs = hs + stmp;
				}
			}
			return hs.toUpperCase();
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(generateJuhuasuanCode(5));

		// String s = StringEncoder.encrypt("6666");
		// System.out.println(s);
	}
}
