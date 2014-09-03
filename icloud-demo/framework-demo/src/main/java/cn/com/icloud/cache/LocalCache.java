package cn.com.icloud.cache;

import java.lang.ref.SoftReference;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月3日 上午8:58:19
 */
public class LocalCache {
	static ConcurrentHashMap<String, SoftReference<String>> caches = new ConcurrentHashMap<String, SoftReference<String>>();

	// static ConcurrentHashMap<String, String> caches = new
	// ConcurrentHashMap<String, String>();

	static void put() {
		long i = 0;
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		s = s + s + s + s + s;
		while (true) {
			i++;
			caches.put(i + "", new SoftReference<String>(s + " " + i + ""));
			// caches.put(i + "", s + " " + i + "");
			if (i % 10000 == 0) {
				System.out.println("runnig=" + i + ",cache size = "
						+ caches.size());
			}
			if (i > 3000000) {
				break;
			}
		}

		Set<Entry<String, SoftReference<String>>> entrySet = caches.entrySet();
		long count = 0;
		long no = 0;
		for (Entry<String, SoftReference<String>> entry : entrySet) {
			if (entry.getValue().get() != null) {
				count++;
			} else {
				no++;
			}
		}
		System.out.println("ok ================");
		System.out.println("count = " + count + " ; no = " + no);
	}

	public static void main(String[] args) {
		put();
	}
}
