package com.icloud.leetcode.array;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月23日 上午11:30:58
 */
public class SingleNum {
	public int singleNumber(int[] A) {
		int a = 0;
		for (int i = 0; i < A.length; i++) {
			a = a ^ A[i];
		}
		return a;
	}

	public static void main(String[] args) {
		SingleNum num = new SingleNum();
		int[] A = { 2, 3, 2, 3, 4 };
		System.out.println(num.singleNumber(A));
	}
}
