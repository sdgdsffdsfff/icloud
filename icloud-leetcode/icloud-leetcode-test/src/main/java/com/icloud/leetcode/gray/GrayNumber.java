package com.icloud.leetcode.gray;

import java.util.ArrayList;
import java.util.List;

public class GrayNumber {
	public List<Integer> grayCode(int n) {
		List<Integer> list = new ArrayList<Integer>();
		if (n == 0) {
			list.add(0);
			return list;
		}
		List<Integer> tmpList = grayCode(n - 1);
		int addNumber = 1 << (n - 1);
		list.addAll(tmpList);
		for (int i = tmpList.size() - 1; i >= 0; i--) {
			list.add(tmpList.get(i) + addNumber);
		}
		return list;
	}

	public static void main(String[] args) {
		GrayNumber instance = new GrayNumber();
		List<Integer> list = instance.grayCode(2);
		for (Integer in : list) {
			System.out.println(in);
		}
	}
}
