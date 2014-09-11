package com.cninfo.media.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import com.cninfo.media.mongo.entity.Media;
import com.cninfo.media.mongo.entity.MediaType;
import com.travelzen.tops.preformance.PerformanceRunnable;
import com.travelzen.tops.preformance.ThreadStat;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:59:53
 */
public class MongoFilePerformanceRunnable extends PerformanceRunnable {

	/**
	 * @param threadName
	 * @param threadStat
	 */
	public MongoFilePerformanceRunnable(String threadName, ThreadStat threadStat) {
		super(threadName, threadStat);
	}

	@Override
	public String doRun() {
		try {
			testSave();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void testSave() throws FileNotFoundException {
		Media media = new Media();
		InputStream inputStream = new FileInputStream(new File(
				"D:\\test\\media\\test1.txt"));
		media.setInputStream(inputStream);
		media.setFilename("1.txt");
		media.setMediaId(System.currentTimeMillis() + ""
				+ Math.ceil(Math.random() * 10000));
		media.setType("txt");
		media.setCreateTime(new Date());
		media.setLength(5555);
		PerformanceTest.mediaDao.addMedia(media);
	}

}
