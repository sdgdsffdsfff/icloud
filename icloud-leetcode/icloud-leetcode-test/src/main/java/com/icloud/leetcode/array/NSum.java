package com.icloud.leetcode.array;

import java.util.Arrays;
import java.util.List;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月23日 上午11:27:49
 */
public class NSum {
	// Input: numbers={2, 7, 11, 15}, target=9
	// Output: index1=1, index2=2
	/**
	 * 思路： 算法：最初我们找到数组的第一个数字和最后一个数字。当两个数字的和大于输入的数字时，把较大的数字往前移动；当两个数字的和小于数字时，
	 * 把较小的数字往后移动；当相等时，打完收工。这样扫描的顺序是从数组的两端向数组的中间扫描。 Given an array of integers,
	 * find two numbers such that they add up to a specific target number.
	 * 
	 * @param numbers
	 * @param target
	 * @return int[]
	 * @throws
	 */
	public int[] twoSum(int[] numbers, int target) {
		Arrays.sort(numbers);
		int i = 0;
		int j = numbers.length - 1;
		while (i < j) {
			int tmp = numbers[i] + numbers[j];
			if (tmp == target) {
				int[] a = { i, j };
				return a;
				// break;
			} else if (tmp > target) {
				j--;
			} else {
				i++;
			}
		}
		return null;
	}

	/**
	 * For example, given array S = {-1 0 1 2 -1 -4},
	 * 
	 * A solution set is: (-1, 0, 1) (-1, -1, 2) 思路: 1.将数组排序， 2.a 遍历
	 * 数组a[0]....a[n-1]; 3.当 a=a[i] 时 后面的问题 就是 : a[i+1] 到 a[n-1]中 b+c =-a （编程之美
	 * 2.12 快速寻找满足条件的两个数 ） 记 b=a[j]=a[i-1] c=a[k]=a[n-1] 若 b+c < -a ，j++; b+c >
	 * -a ，j--; b+c=-a 记录下来，并j++; 4.还有一个问题 就是unique triplet, 所以 a=a[i]
	 * 要判断是否和a[i-1]相等，若相等，子问题已经解答。 也要判断 b和c 是否和之前的相同，若相同，就已经判断过了。
	 * 
	 * @param num
	 * @return List<List<Integer>>
	 * @throws
	 */
	public List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		return null;
	}

	public static void main(String[] args) {
		NSum sum3 = new NSum();
		int[] num = { 3, 4, 2 };
		sum3.twoSum(num, 6);
	}
}
