package com.travelzen.tops.preformance;

public abstract class PerformanceRunnable implements Runnable {
	private String threadName = null;
	private ThreadStat threadStat = null;

	public PerformanceRunnable(String threadName, ThreadStat threadStat) {
		this.threadName = threadName;
		this.threadStat = threadStat;
	}

	private void sendResult(String result) {
		threadStat.sendResult(result);
	}

	private boolean checkToStart() {
		return threadStat.checkToStart();
	}

	@Override
	public void run() {
		// System.out.println("Thread - " + name + " is start");
		String result = null;
		while (checkToStart()) {
			result = doRun();
			sendResult(result);
		}
		// System.out.println("Thread - " + name + " is dead");
	}

	public abstract String doRun();

}
