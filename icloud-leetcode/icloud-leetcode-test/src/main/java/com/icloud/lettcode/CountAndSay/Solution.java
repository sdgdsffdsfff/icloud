package com.icloud.lettcode.CountAndSay;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月22日 下午6:23:12
 */
public class Solution {
	public String countAndSay(int n) {
		String ret = "1";
		int j = 1;
		while (j < n) {
			ret = revolution(ret);
			j++;
		}
		return ret;
	}

	/**
	 * @param ret
	 * @return String
	 * @throws
	 */
	private String revolution(String ret) {
		StringBuffer sb = new StringBuffer();
		char lastChar = ret.charAt(0);
		int count = 1;
		for (int i = 1; i < ret.length(); i++) {
			char ch = ret.charAt(i);
			if (ch != lastChar) {
				sb.append(count + "" + (lastChar - '0'));
				count = 1;
				lastChar = ch;
			} else {
				count++;
			}
		}
		sb.append(count + "" + (lastChar - '0'));
		return sb.toString();
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.countAndSay(5));
	}
}
