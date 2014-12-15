package com.icloud.leetcode.array;

public class ArraySolution {
	// 非递归
	public int binarySearch(int[] A, int start, int end, int target) {
		if (start > end)
			return -1;
		while (start <= end) {
			int mid = (start + end) >> 1;
			if (A[mid] == target)
				return mid;
			if (A[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public int find(int[] A, int start, int end, int target) {
		if (start > end) {
			return -1;
		}
		int idx = -1;
		if (A[start] <= A[end]) { // 二分查找即可
			return binarySearch(A, start, end, target);
		} else { // 部分
			int mid = (start + end) >> 1;
			if (A[mid] == target) {
				idx = mid;
			} else {
				idx = find(A, start, mid - 1, target);
				if (idx == -1) {
					idx = find(A, mid + 1, end, target);
				}
			}
		}
		return idx;

	}

	public int search(int[] A, int target) {
		int start = 0;
		int end = A.length;
		return find(A, start, end - 1, target);
	}

	/**
	 * 找出已经排序好数组的中位数 要求是log(m+n)
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedianSortedArrays(int A[], int B[]) {
		int lenA = A.length;
		int lenB = B.length;
		// int k = (lenA + lenB) / 2;
		int total = lenA + lenB;
		int k = total / 2;
		if (total % 2 > 0) {
			// k = 3;
			k = k + 1;
			return findKMin(A, 0, lenA, B, 0, lenB, k);
		} else {
			System.out.println(k + "  " + findKMin(A, 0, lenA, B, 0, lenB, k));
			System.out.println(k + 1 + "  "
					+ findKMin(A, 0, lenA, B, 0, lenB, k + 1));

			double a = findKMin(A, 0, lenA, B, 0, lenB, k)
					+ findKMin(A, 0, lenA, B, 0, lenB, k + 1);
			// System.out.println(a);
			return a / 2.0;
		}

	}

	private int min(int a, int b) {
		return a > b ? b : a;
	}

	private double findKMin(int[] a, int startA, int endA, int[] b, int startB,
			int endB, int k) {
		int lenA = endA - startA;
		int lenB = endB - startB;
		if (lenA > lenB) {
			return findKMin(b, startB, endB, a, startA, endA, k);
		}
		if (lenA == 0) {
			return b[startB + k - 1];
		}
		if (k <= 1) {
			return min(a[startA], b[startB]);
		}
		int pb = k / 2;
		// if (k % 2 > 0)
		// pb = pb + 1;
		int pa = min(pb, lenA);

		if (a[startA + pa - 1] < b[startB + pa - 1]) {
			return findKMin(a, pa + startA, endA, b, startB, endB, k - pa);
		} else if (a[pa - 1] > b[pa - 1]) {
			return findKMin(a, startA, endA, b, pa + startB, endB, k - pa);
		} else {
			return findKMin(a, startA, endA, b, pa + startB, endB, k - pa);
		}
		// return a[pa + startA - 1];
	}

	public static void main(String[] args) {
		int[] a = { 1, 2 };
		int[] b = { 1, 2 };
		ArraySolution solution = new ArraySolution();
		double median = solution.findMedianSortedArrays(a, b);
		System.out.println(median);
	}
}
