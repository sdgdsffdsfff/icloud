package com.icloud.ehcache.client;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EhcacheClient {
	private CacheManager cacheManager;

	private Cache cache;

	// singleton
	private static class SingletonHolder {
		static final EhcacheClient INSTANCE = new EhcacheClient();
		static {
			INSTANCE.init();
		}
	}

	public static EhcacheClient getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public void init() {
		ApplicationContext app = new ClassPathXmlApplicationContext(
				"spring/framework-ehcache-ctx-min.xml");
		EhCacheCacheManager ehCacheManager = (EhCacheCacheManager) app
				.getBean("cacheManager");
		cacheManager = ehCacheManager.getCacheManager();
		cache = cacheManager.getCache("com.icloud.singleIcloudCache");
	}

	public void save(String key, Object value) {
		cache.put(new Element(key, value));
	}

	public Object get(String key) {
		Element element = cache.get(key);
		Object obj = element.getObjectValue();
		return obj;
	}
}
