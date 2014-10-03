package com.icloud.stock.dao.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanDaoTest extends StockDaoTest {
	@Resource(name = "juhuasuanUrlDao")
	private IJuhuasuanUrlDao juhuasuanUrlDao;

	@Test
	public void save() {
		JuhuasuanUrl t = new JuhuasuanUrl();
		t.setCreateTime(new Date());
		t.setDesText("adf");
		t.setIcloudUrl("adf");
		t.setName("niaho");
		t.setTaobaoUrl("taobao");
		t.setUserId(3);
		t.setUpdateTime(t.getCreateTime());
		System.out.println("---start---");
		juhuasuanUrlDao.save(t);
		System.out.println("---end ---");
	}
}
