package com.cninfo.media.performance;

import com.travelzen.tops.preformance.LoadPerformanceUnit;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月15日 上午11:28:09
 */
public class PerformanceMongoGetTest {
	public static void startToPerforManceTest() {
		int sum = 1000000;
		int thread_num = 50;

		MediaIdsFile.getMediaIds(sum);
		LoadPerformanceUnit LoadPerformanceUnit = new LoadPerformanceUnit(
				thread_num, sum, MongoFileOutputPerformanceRunnable.class);
		LoadPerformanceUnit.run();
	}

	public static void main(String[] args) {
		startToPerforManceTest();
	}
}
