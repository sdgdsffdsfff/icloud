package com.icloud.leetcode.ReverseInteger;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年11月7日 下午4:08:45
 */
public class JumpGame {
	public boolean canJump(int[] A) {
		int len = A.length - 1;
		int max = 0;

		for (int i = 0; i <= len; i++) {
			if (max >= i) {
				max = (max > (i + A[i])) ? max : i + A[i];
			}
			if (max >= len) {
				return true;
			}
		}
		if (max >= len) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		JumpGame game = new JumpGame();
		int[] A = { 2, 3, 1, 1, 4 };
		boolean flag = game.canJump(A);
		System.out.println("flag = " + flag);
	}
}
