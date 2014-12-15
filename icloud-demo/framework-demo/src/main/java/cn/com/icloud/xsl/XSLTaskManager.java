package cn.com.icloud.xsl;

import com.icloud.framework.performance.runnable.TaskManager;
import com.icloud.framework.performance.runnable.TaskRunnable;
import com.icloud.framework.performance.runnable.ThreadStat;

public class XSLTaskManager extends TaskManager {

	public XSLTaskManager(int thread_limit, int work_sum) {
		super(thread_limit, work_sum);
		
	}

	@Override
	public TaskRunnable getPerformanceRunnable(String threadName,
			ThreadStat threadStat) {
		return new XSLRunnable(threadName, threadStat);
	}

}
