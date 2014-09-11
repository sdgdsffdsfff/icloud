package com.cninfo.media.performance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cninfo.media.dao.IMediaDao;
import com.travelzen.tops.preformance.LoadPerformanceUnit;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:52:59
 */
public class PerformanceTest {
	static ClassPathXmlApplicationContext svContext = new ClassPathXmlApplicationContext("classpath*:spring/shtb-mongo-ctx-min.xml");
	public static IMediaDao mediaDao = (IMediaDao) svContext.getBean("mediaDao");

	public static void startToPerforManceTest() {
		LoadPerformanceUnit LoadPerformanceUnit = new LoadPerformanceUnit(100,
				1000000, MongoFilePerformanceRunnable.class);
		LoadPerformanceUnit.run();
	}

	public static void main(String[] args) {
		startToPerforManceTest();
	}
}
