package com.icloud.leetcode.CountAndSay;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月22日 下午6:23:12
 */
public class Solution {
	public String countAndSay(int n) {
		String ret = "1";
		int j = 1;
		while (j < n) {
			ret = revolution(ret);
			j++;
		}
		return ret;
	}

	/**
	 * @param ret
	 * @return String
	 * @throws
	 */
	private String revolution(String ret) {
		StringBuffer sb = new StringBuffer();
		char lastChar = ret.charAt(0);
		int count = 1;
		for (int i = 1; i < ret.length(); i++) {
			char ch = ret.charAt(i);
			if (ch != lastChar) {
				sb.append(count + "" + (lastChar - '0'));
				count = 1;
				lastChar = ch;
			} else {
				count++;
			}
		}
		sb.append(count + "" + (lastChar - '0'));
		return sb.toString();
	}

	public int sqrt(int x) {
		long i = 0;
		long j = x / 2 + 1;
		while (i < j) {
			long mid = ((i + j) / 2);
			long sq = mid * mid;
			if (sq == x)
				return (int) mid;
			else if (sq < x)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return (int) (i * i > x ? i - 1 : i);
	}

	public void merge(int A[], int m, int B[], int n) {
		int pA = m - 1;
		int pB = n - 1;
		int p = pA + pB + 1;

		while (pA >= 0 && pB >= 0) {
			if (A[pA] > B[pB]) {
				A[p] = A[pA];
				pA--;
			} else {
				A[p] = B[pB];
				pB--;
			}
			p--;
		}
		// while (pA >= 0) {
		// A[p] = A[pA];
		// pointerA--;
		// p--;
		// }
		while (pB >= 0) {
			A[p] = B[pB];
			pB--;
			p--;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.sqrt(2147395599));
	}
}
