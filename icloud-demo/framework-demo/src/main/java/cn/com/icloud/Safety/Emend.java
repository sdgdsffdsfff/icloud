package cn.com.icloud.Safety;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年8月6日 下午3:29:26
 */
public class Emend {
	static List<String> list = null;
	static long[] inVs = null;
	static long[] outVs = null;
	static long[] totalVs = null;
	static long DEFAULT_VALUE = Long.MIN_VALUE;
	static DecimalFormat fnum = new DecimalFormat("##0.00");

	public static void input2() {
		list = new ArrayList<String>();
		list.add("3;8;?;?");
		list.add("1;0.00;51.90;1945.45");
		list.add("2;0.00;1000.00;?");
		list.add("4;9;10;800");
		list.add("5;9;?;?");
	}

	/**
	 * 输入
	 * 
	 * void
	 * 
	 * @throws
	 */
	public static void input() {
		System.out.println("流水记录ID;收入;支出;余额");
		list = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		do {
			String str = scanner.nextLine();
			// if (str.equals("")) {
			// break;
			// }
			list.add(str);
			if (list.size() >= 25) {
				break;
			}
		} while (true);
	}

	public static long getLong(String str) {
		if ("?".equalsIgnoreCase(str)) {
			return DEFAULT_VALUE;
		}
		// return Float.parseFloat(str);
		float f = Float.parseFloat(str);
		return (long) (f * 100);
	}

	// 输出样例：
	//
	// 1;0.00;51.90;1945.45
	// 2;0.00;1000.00;945.45
	public static void print() {
		System.out.println("流水记录ID;收入;支出;余额");
		int len = list.size();
		for (int i = 0; i < len; i++) {
			System.out.println((i + 1) + ";" + p(inVs[i]) + ";" + p(outVs[i])
					+ ";" + p(totalVs[i]));
		}
	}

	public static String p(long s) {
		if (s == DEFAULT_VALUE)
			return "?";
		return fnum.format(s / 100.0);
	}

	public static void process() {
		int size = list.size();
		inVs = new long[size];
		outVs = new long[size];
		totalVs = new long[size];
		/**
		 * 预处理数据
		 */
		int i = 0;
		for (String str : list) {
			String[] split = str.split(";");
			inVs[i] = getLong(split[1]);
			outVs[i] = getLong(split[2]);
			totalVs[i] = getLong(split[3]);
			i++;
		}
		/**
		 * process()
		 */
		emend(0, size - 1);
		print();
		clear();
	}

	/**
	 * @param i
	 * @param size
	 *            void
	 * @throws
	 */
	private static void emend(int start, int end) {
		/**
		 * 找到第一个东西，便进行修复
		 */
		boolean ok = false;
		int okFlag = -1;
		for (int i = start; i < end; i++) {
			if (fix(i)) {
				ok = true;
				okFlag = i;
				break;
			}
		}
		if (ok) {
			// 向左修复，
			int tmp = -1;
			for (int i = okFlag - 1; i >= start; i--) {
				if (!fix(i)) {
					tmp = i + 1;
					break;
				}
			}
			if (tmp > start) {
				emend(start, tmp);
			}
			tmp = 9999999;
			for (int i = okFlag + 1; i < end; i++) {
				if (!fix(i)) {
					tmp = i;
					break;
				}
			}
			if (tmp < end) {
				emend(tmp, end);
			}
		} else {
			// 没法修复
		}

	}

	/**
	 * fix 有且只有一个，便可以进行修复， void
	 * 
	 * @throws
	 */
	private static boolean fix(int i) {
		int flag = 0;
		if (inVs[i + 1] != DEFAULT_VALUE)
			flag++;
		if (outVs[i + 1] != DEFAULT_VALUE)
			flag++;
		if (totalVs[i + 1] != DEFAULT_VALUE)
			flag++;
		if (totalVs[i] != DEFAULT_VALUE)
			flag++;
		if (flag == 3) {
			if (totalVs[i] == DEFAULT_VALUE) {
				totalVs[i] = totalVs[i + 1] + outVs[i + 1] - inVs[i + 1];
			} else if (totalVs[i + 1] == DEFAULT_VALUE) {
				totalVs[i + 1] = totalVs[i] + inVs[i + 1] - outVs[i + 1];
			} else if (inVs[i + 1] == DEFAULT_VALUE) {
				inVs[i + 1] = totalVs[i + 1] + outVs[i + 1] - totalVs[i];
			} else if (outVs[i + 1] == DEFAULT_VALUE) {
				outVs[i + 1] = totalVs[i] + inVs[i + 1] - totalVs[i + 1];
			}
			return true;
		} else if (flag == 4) {
			return true;
		} else {
			return false;
		}

	}

	public static void clear() {
		inVs = null;
		outVs = null;
		totalVs = null;
		list.clear();
	}

	public static void main(String args[]) {
		// input();
		input2();
		process();
	}
}
