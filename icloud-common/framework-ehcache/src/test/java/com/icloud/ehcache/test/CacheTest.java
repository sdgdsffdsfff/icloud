package com.icloud.ehcache.test;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.icloud.ehcache.client.EhcacheClient;

public class CacheTest extends BaseTest {
	@Autowired
	private Cache cache;

	@Test
	public void saveAndgetTest() {
		String key = "hello";
		String value = "world";
		EhcacheClient.getInstance().save(key, value);
		Object obj = EhcacheClient.getInstance().get(key);
		Assert.assertEquals(value, obj);
		System.out.println(obj);
	}

	@Test
	public void autoSaveAndgetTest() {
		String key = "hello";
		String value = "world";
		cache.put(new Element(key, value));
		Element element = cache.get(key);
		Object obj = element.getObjectValue();
		Assert.assertEquals(value, obj);
		System.out.println(obj);
	}
}
