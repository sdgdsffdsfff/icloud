package com.icloud.stock.marketing.runnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadStat {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThreadStat.class);
	private int sum = 0;
	private int running = 0;

	private long startTime = 0;
	private Map<String, Integer> map;
	private int core = 0;
	private int max = 0;

	public ThreadStat(int sum, int core, int max) {
		this.sum = sum;
		this.running = 0;
		map = new HashMap<String, Integer>();
		this.core = core;
		this.max = max;
		startTime = System.currentTimeMillis();
	}

	public synchronized boolean checkToStart() {
		if (running >= sum) {
			return false;
		}
		running++;
		if (running % 50 == 0)
			LOGGER.info("running " + running + "/" + sum);
		return true;
	}

	public synchronized void sendResult(String statusCode) {
		if (statusCode != null) {
			// int statusCode = sendGetRequest.getStatusCode();
			Integer integer = map.get(statusCode);
			if (integer == null) {
				integer = 0;
			}
			integer = integer + 1;
			map.put(statusCode, integer);
		}
	}

	public void print() {
		LOGGER.info("----->>>状态<<<----------");
		// running--;
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			LOGGER.info(entry.getKey() + " : " + entry.getValue());
		}
		LOGGER.info("----->>>统计<<<----------");
		long endTime = System.currentTimeMillis();
		float intervet = (endTime - startTime) / 1000.0f;
		float kps = sum / intervet;
		LOGGER.info("时间：" + intervet + "s,作业:" + sum + ",kps:" + kps
				+ ",实际执行数目" + (running));
		LOGGER.info("线程池参数,core = " + core + ", max = " + max);
	}
}