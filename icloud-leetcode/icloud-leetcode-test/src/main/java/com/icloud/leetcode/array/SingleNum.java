package com.icloud.leetcode.array;

import com.icloud.framework.util.ICloudUtils;

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

	public double monthPricee(double base, double bit) {
		return base * bit / 100;
	}

	public void print(double base, int month, double bit) {
		double current = base;
		for (int i = 0; i < month; i++) {
			double price = monthPricee(current, bit);
			double total = current + price + base;
			System.out.println("第" + (i + 1) + "月，余额:"
					+ ICloudUtils.getDigitalString(current, 2) + ",利息:"
					+ ICloudUtils.getDigitalString(price, 2) + ",存入:" + base
					+ ";总数:" + ICloudUtils.getDigitalString(total, 2));
			current = total;
		}
	}

	public static void main(String[] args) {
		SingleNum num = new SingleNum();
		int[] A = { 2, 3, 2, 3, 4 };
		// System.out.println(num.singleNumber(A));
		num.print(2000, 120, 2);
	}
}
