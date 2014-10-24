package com.icloud.leetcode.triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午12:35:08
 */
public class Triangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> row1 = new ArrayList<Integer>();
		row1.add(1);
		List<Integer> row2 = new ArrayList<Integer>();
		row2.add(1);
		row2.add(1);
		if (numRows == 1) {
			list.add(row1);
		} else if (numRows == 2) {
			list.add(row1);
			list.add(row2);
		} else if (numRows > 2) {
			list.add(row1);
			list.add(row2);
			List<Integer> lastList = row2;
			for (int i = 2; i < numRows; i++) {
				List<Integer> currentList = new ArrayList<Integer>();
				currentList.add(1);
				int len = lastList.size() - 1;
				for (int j = 0; j < len; j++) {
					currentList.add(lastList.get(j) + lastList.get(j + 1));
				}
				currentList.add(1);
				list.add(currentList);
				lastList = currentList;
			}

		}
		return list;
	}

	public static void main(String[] args) {
		Triangle triangle = new Triangle();
		List<List<Integer>> list = triangle.generate(0);
		for (List<Integer> l : list)
			System.out.println(l);
	}
}
