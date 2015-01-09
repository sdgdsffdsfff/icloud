package com.icloud.leetcode.string;

public class ZigZag {
	public int atoi(String str) {
		int max = Integer.MAX_VALUE;
		int min = -Integer.MIN_VALUE;
		long result = 0;
		str = str.trim();
		int len = str.length();
		if (len < 1)
			return 0;
		int start = 0;
		boolean neg = false;

		if (str.charAt(start) == '-' || str.charAt(start) == '+') {
			if (str.charAt(start) == '-')
				neg = true;
			start++;
		}

		for (int i = start; i < len; i++) {
			char ch = str.charAt(i);

			if (ch < '0' || ch > '9')
				break;
			result = 10 * result + (ch - '0');
			if (!neg && result > max)
				return max;
			if (neg && -result < min)
				return min;

		}
		if (neg)
			result = -result;

		return (int) result;
	}

	public String convert(String s, int nRows) {
		if (nRows == 0)
			return null;
		if (nRows == 1)
			return s;
		int size = s.length();
		StringBuffer sb = new StringBuffer();

		nRows--;
		for (int i = 0; i <= nRows; i++) {

			int currentIndex = i;
			int count = 0;
			while (currentIndex < size) {
				int newIndex = 0;
				if (count % 2 == 0) {
					newIndex = currentIndex + 2 * (nRows - i);
				} else {
					newIndex = currentIndex + 2 * (i);
				}
				count++;
				if (currentIndex != newIndex) {
					sb.append(s.charAt(currentIndex));
					currentIndex = newIndex;
				}
			}
		}
		return sb.toString();
	}

	public int titleToNumber(String s) {
		int len = s.length();
		if (len < 1)
			return 0;
		int num = 0;
		for (int i = 0; i < len; i++) {
			char ch = s.charAt(i);

			int ss = ch - 'A' + 1;
			num = num * 26 + ss;
		}
		return num;
	}

	public static void main(String[] args) {
		String s = "PAYPALISHIRING";
		int nRows = 3;
		s = "A";
		nRows = 1;
		ZigZag zigZag = new ZigZag();
		// s = zigZag.convert(s, nRows);
		// System.out.println(s);
		System.out.println(zigZag.atoi(" 10522545459"));
	}
}
