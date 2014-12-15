package com.icloud.leetcode.string;

import java.util.HashMap;

public class StringSolution {
	public int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * 时间窗口的概念
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int len = s.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(256);
		int max = -1;
		int start = 0;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) { // 说明含有的东西重复了，这样需要去掉重复之前的东西
				for (; start < i; start++) {
					if (s.charAt(start) == c) {// 可以停止了
						start++;
						break;
					} else {
						map.remove(s.charAt(start));
					}
				}

			} else {
				map.put(c, i);
				max = max(max, i - start + 1);
			}
		}

		return max;
	}

	/**
	 * 获得最常的子串的长度，沒有重複
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StringSolution solution = new StringSolution();
		String s = "afddsafasf";
		int len = solution.lengthOfLongestSubstring(s);
		System.out.println(len);
	}

}
