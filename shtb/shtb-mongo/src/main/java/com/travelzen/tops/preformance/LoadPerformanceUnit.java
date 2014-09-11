package com.travelzen.tops.preformance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadPerformanceUnit {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoadPerformanceUnit.class);

	private ThreadPoolExecutor threadPoolExecutor = null;
	private ThreadStat threadStat = null;
	private int limit;
	private int sum;
	private Class c;

	public LoadPerformanceUnit(int thread_limit, int work_sum, Class c) {
		this.limit = thread_limit;
		this.sum = work_sum;
		this.c = c;
		threadPoolExecutor = new ThreadPoolExecutor(this.limit, this.limit, 5,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		threadStat = new ThreadStat(this.sum,
				threadPoolExecutor.getCorePoolSize(),
				threadPoolExecutor.getMaximumPoolSize());
	}

	public void run() {
		try {
			doTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doTest() throws Exception {
		LOGGER.info(">>>> start <<<<<<");
		List<PerformanceRunnable> threadList = new ArrayList<PerformanceRunnable>();
		for (int i = 0; i < limit; i++) {
			PerformanceRunnable thread = getPerformanceRunnable("thread_" + i,
					threadStat);
			if (thread != null)
				threadList.add(thread);
		}
		for (PerformanceRunnable thread : threadList) {
			threadPoolExecutor.submit(thread, null);
		}
		long completedTaskCount = 0;
		int count = 0;
		while (completedTaskCount < limit) {
			Thread.sleep(200);
			completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
			count++;
			if (count % 50 == 0)
				LOGGER.info("completedTaskCount = " + completedTaskCount
						+ ",active count = "
						+ threadPoolExecutor.getActiveCount());
		}
		threadPoolExecutor.shutdown();
		LOGGER.info("<<<<<<<<< game over >>>>>>>>>");
		threadStat.print();
	}

	public PerformanceRunnable getPerformanceRunnable(String threadName,
			ThreadStat threadStat) {
		Class[] classParas = { String.class, ThreadStat.class };
		Object[] paras = { threadName, threadStat };
		Constructor<PerformanceRunnable> con = null;
		// try {
		try {
			con = c.getConstructor(classParas);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// } catch (NoSuchMethodException | SecurityException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }// 获取使用当前构造方法来创建对象的Constructor对象，用它来获取构造函数的一些
		Object o = null;
		if (con != null) {
			// try {
			try {
				o = con.newInstance(paras);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// } catch (InstantiationException | IllegalAccessException |
			// IllegalArgumentException | InvocationTargetException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// o = null;
			// }
		}
		if (o != null) {
			return (PerformanceRunnable) o;
		}
		return null;
	}
}
