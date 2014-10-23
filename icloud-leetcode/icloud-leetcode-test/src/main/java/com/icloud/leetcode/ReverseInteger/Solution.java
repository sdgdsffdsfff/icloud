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

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return null;
		}
		ListNode rootNode = new ListNode(0);
		ListNode tmpNode = rootNode;
		int update = 0;
		while (l1 != null || l2 != null) {
			int left = 0;
			int right = 0;
			if (l1 != null) {
				left = l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				right = l2.val;
				l2 = l2.next;
			}
			int current = left + right + update;
			if (current > 9) {
				current = current - 10;
				update = 1;
			} else {
				update = 0;
			}
			tmpNode.next = new ListNode(current);
			tmpNode = tmpNode.next;
		}
		if (update > 0) {
			tmpNode.next = new ListNode(update);
			tmpNode = tmpNode.next;
		}
		return rootNode.next;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.reverse(100));
	}

}
