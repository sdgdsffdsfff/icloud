package com.cninfo.media.performance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cninfo.media.dao.IMediaDao;
import com.travelzen.tops.preformance.LoadPerformanceUnit;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:52:59
 */
public class PerformanceMongoSaveTest {

	public static void startToPerforManceTest() {
		int sum = 1000000;
		int thread_num = 100;
		
		LoadPerformanceUnit LoadPerformanceUnit = new LoadPerformanceUnit(
				thread_num, sum, MongoFilePerformanceRunnable.class);
		LoadPerformanceUnit.run();
	}

	public static void main(String[] args) {
		startToPerforManceTest();
	}
}
