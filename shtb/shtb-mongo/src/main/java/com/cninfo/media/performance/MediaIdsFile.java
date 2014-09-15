package com.cninfo.media.performance;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.LogFileReader;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月15日 上午10:08:43
 */
public class MediaIdsFile {
	static LogFileReader reader = new LogFileReader("/opt/performance/mongo/copy.txt");
	private static List<String> ids = null;
	private static int i = 0;
	private static int size = 0;

	static synchronized String getMediaId() {
		return reader.readLine();
	}

	public static synchronized String getId() {
		if (i < size) {
			return ids.get(i++);
		}
		return null;
	}

	static List<String> getMediaIds(int limit) {
		List<String> list = new ArrayList<String>();
		String line = null;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			list.add(line);
			count++;
			if (count >= limit)
				break;
		}
		ids = list;
		i = 0;
		size = list.size();
		return list;
	}

}
