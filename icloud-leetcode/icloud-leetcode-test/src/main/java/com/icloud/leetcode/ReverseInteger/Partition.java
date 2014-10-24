package com.icloud.leetcode.ReverseInteger;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午12:47:42
 */
public class Partition {
	public ListNode partition(ListNode head, int x) {
		ListNode rootLessNode = null;
		ListNode lessNode = null;
		ListNode rootgreaterNodeNode = null;
		ListNode greaterNode = null;
		while (head != null) {
			if (head.val < x) {
				if (rootLessNode == null) {
					rootLessNode = head;
					lessNode = head;
				} else {
					lessNode.next = head;
					lessNode = head;
				}
			} else {
				if (rootgreaterNodeNode == null) {
					rootgreaterNodeNode = head;
					greaterNode = head;
				} else {
					greaterNode.next = head;
					greaterNode = head;
				}
			}
			ListNode tmp = head.next;
			head.next = null;
			head = tmp;
		}
		if (rootLessNode != null && rootgreaterNodeNode != null) {
			lessNode.next = rootgreaterNodeNode;
			return rootLessNode;
		}
		if (rootLessNode != null)
			return rootLessNode;
		if (rootgreaterNodeNode != null)
			return rootgreaterNodeNode;
		return null;
	}

}
