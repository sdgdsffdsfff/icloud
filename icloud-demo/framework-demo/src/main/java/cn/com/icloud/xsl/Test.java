package cn.com.icloud.xsl;

public class Test {
	public void test(int number, int sum) {
		XSLTaskManager manager = new XSLTaskManager(number, sum);
		manager.run();
	}

	public void test() {
		int number = 10;
		int sum = 20000;
		test(number, sum);
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.test();
	}
}
