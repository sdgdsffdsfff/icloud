package com.icloud.framework.performance.runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TaskManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TaskManager.class);

	private ThreadPoolExecutor threadPoolExecutor = null;
	private ThreadStat threadStat = null;
	private int limit;
	private int sum;

	// private Class c;
	// , Class c
	public TaskManager(int thread_limit, int work_sum) {
		this.limit = thread_limit;
		this.sum = work_sum;
		// this.c = c;
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
		List<TaskRunnable> threadList = new ArrayList<TaskRunnable>();
		for (int i = 0; i < limit; i++) {
			TaskRunnable thread = getPerformanceRunnable("thread_" + i,
					threadStat);
			if (thread != null)
				threadList.add(thread);
		}
		for (TaskRunnable thread : threadList) {
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

	public abstract TaskRunnable getPerformanceRunnable(String threadName,
			ThreadStat threadStat);
	// public TaskRunnable getPerformanceRunnable(String threadName,
	// ThreadStat threadStat) {
	// Class[] classParas = { String.class, ThreadStat.class };
	// Object[] paras = { threadName, threadStat };
	// Constructor<TaskRunnable> con = null;
	// try {
	// con = c.getConstructor(classParas);
	// } catch (NoSuchMethodException | SecurityException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }// 获取使用当前构造方法来创建对象的Constructor对象，用它来获取构造函数的一些
	// Object o = null;
	// if (con != null) {
	// try {
	// o = con.newInstance(paras);
	// } catch (InstantiationException | IllegalAccessException
	// | IllegalArgumentException | InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// o = null;
	// }
	// }
	// if (o != null) {
	// return (TaskRunnable) o;
	// }
	// return null;
	// }

}
