package com.cninfo.media.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.cninfo.media.mongo.entity.Media;
import com.icloud.framework.util.FileUtils;
import com.travelzen.tops.preformance.PerformanceRunnable;
import com.travelzen.tops.preformance.ThreadStat;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:59:53
 */
public class MongoFilePerformanceRunnable extends PerformanceRunnable {
	public static byte[] content = null;
	static {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(
					"/opt/performance/mongo/test1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		content = FileUtils.readByte(inputStream);
	}

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
//		InputStream inputStream = new FileInputStream(new File(
//				"/opt/performance/mongo/test1.txt"));
		media.setContent(content);
		String filename = System.currentTimeMillis() + ""
				+ Math.ceil(Math.random() * 10000);
		media.setFilename(filename + ".txt");
		media.setMediaId(filename);
		media.setType("txt");
		media.setCreateTime(new Date());
		media.setLength(content.length);
		PerformanceTest.mediaDao.addMedia(media);
		// try {
		// inputStream.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
