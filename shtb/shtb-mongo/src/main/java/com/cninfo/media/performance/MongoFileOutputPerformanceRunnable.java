package com.cninfo.media.performance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cninfo.media.mongo.entity.Media;
import com.icloud.framework.util.LogFileWriter;
import com.travelzen.tops.preformance.PerformanceRunnable;
import com.travelzen.tops.preformance.ThreadStat;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月15日 上午9:23:58
 */
public class MongoFileOutputPerformanceRunnable extends PerformanceRunnable {
	/**
	 * @param threadName
	 * @param threadStat
	 */
	public MongoFileOutputPerformanceRunnable(String threadName,
			ThreadStat threadStat) {
		super(threadName, threadStat);
	}

	public static Logger logger = LoggerFactory
			.getLogger(MongoFileOutputPerformanceRunnable.class);

	@Override
	public String doRun() {
		String id = MediaIdsFile.getId();
		try {
			if (id != null) {
				getMedia(id);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void getFiles() {
		// String line = null;
		// while ((line = MediaIdsFile.getMediaId()) != null) {
		// logger.info(line);
		// }
		// logger.info(" getFiles end ---");
		List<String> list = MediaIdsFile.getMediaIds(10);
		for (String id : list) {
			// logger.info(id);
			try {
				getMedia(id);
				logger.info(" id =  {} ", id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getMedia(String mediaId) throws IOException {
		Media media = BeanUtils.mediaDao.getMedia(mediaId);
		InputStream inputStream = media.getInputStream();

		int limit = 2048;
		byte[] bytes = new byte[(int) limit];
		int numRead = 0;
		do {
			numRead = inputStream.read(bytes, 0, limit);
		} while (numRead == limit);

		// Close the input stream and return bytes
		inputStream.close();
	}

	public static void saveAllFiles() {
		logger.info("----start---");
		Long count = BeanUtils.mediaDao.count();
		logger.info("count = {}", count);
		logger.info("---end-----");
		LogFileWriter writer = new LogFileWriter("D:/test/media/allId.txt");
		int limit = 100000;

		for (int i = 0; i < count; i += limit) {
			List<String> mediaIds = BeanUtils.mediaDao.getMediaIds(i, limit);
			for (String id : mediaIds) {
				writer.apendln(id);
			}
			logger.info("running , count = {}, i = {}", count, i);
		}
		writer.close();
		logger.info("---end 222-----");
	}

	public static void main(String[] args) {
		// saveAllFiles();
		getFiles();
	}

}
