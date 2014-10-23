package com.icloud.leetcode.ReverseInteger;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月23日 上午9:03:55
 */
public class Solution {
	public int reverse(int x) {
		boolean flag = true;
		if (x < 0) {
			flag = false;
			x = -x;
		}
		int newX = 0;
		while (x > 0) {
			newX = newX * 10 + x % 10;
			x = x / 10;
		}
		if (!flag) {
			newX = -newX;
		}
		return newX;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.reverse(100));
	}

}
