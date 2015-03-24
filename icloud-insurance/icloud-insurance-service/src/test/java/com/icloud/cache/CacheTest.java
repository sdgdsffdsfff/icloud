package com.icloud.cache;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.icloud.insurance.service.BaseTest;

public class CacheTest extends BaseTest {
	@Resource(name = "cacheManager")
	private EhCacheCacheManager cacheManager;

	@Test
	public void set() {
		CacheManager cacheManager2 = cacheManager.getCacheManager();
		Cache cache = (Cache) cacheManager2.getCache("com.icloud.MethodCache");

		String key = "hello";
		String value = "world";
		cache.put(new Element(key, value));
		Element element = cache.get(key);
		Object obj = element.getObjectValue();
		Assert.assertEquals(value, obj);
		System.out.println(obj);
	}
}
