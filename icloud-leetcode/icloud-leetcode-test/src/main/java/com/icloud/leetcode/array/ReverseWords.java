package com.icloud.leetcode.array;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年11月7日 上午10:41:19
 */
public class ReverseWords {
	public String reverseWords(String s) {
		if (s == null)
			return s;
		s = s.trim();
		char[] charArray = s.toCharArray();
		int len = charArray.length;
		if (len == 0)
			return s;
		len = deleteSequenceSpace(charArray, len);
		int start = 0;
		reverseArray(charArray, 0, len - 1);

		for (int i = 0; i < len; i++) {
			if (charArray[i] == ' ') {
				reverseArray(charArray, start, i - 1);
				start = i + 1;
			}
		}
		if (start != len) {
			reverseArray(charArray, start, len - 1);
		}

		return new String(charArray, 0, len);
	}

	public void reverseArray(char[] charArray, int start, int end) {
		for (; start < end; start++, end--) {
			char ch = charArray[start];
			charArray[start] = charArray[end];
			charArray[end] = ch;
		}
	}

	public int deleteSequenceSpace(char[] charArray, int len) {
		int last = 0;
		for (int i = 1; i < len; i++) {
			if (charArray[i] == ' ' && charArray[last] == ' ') {

			} else {
				last++;
				charArray[last] = charArray[i];
			}
		}
		return last + 1;
	}

	public static void main(String[] args) {
		ReverseWords words = new ReverseWords();
		String s = "hi!";
		// s = "  ";
		s = words.reverseWords(s);
		System.out.println(s);
	}
}
