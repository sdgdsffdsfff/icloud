package com.icloud.leetcode.array;

public class StockPrice {
	// Say you have an array for which the ith element is the price of a given
	// stock on day i.
	//
	public int maxProfit(int[] prices) {
		int len = prices.length;
		if (len <= 1)
			return 0;
		int res = 0;
		for (int i = 0; i < (len - 1); i++) {
			if (prices[i + 1] - prices[i] > 0) {
				res += prices[i + 1] - prices[i];
			}
		}
		return res;
	}
}
