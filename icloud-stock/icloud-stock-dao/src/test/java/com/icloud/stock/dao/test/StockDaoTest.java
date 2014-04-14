package com.icloud.stock.dao.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.stock.dao.IStockDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-*" })
public class StockDaoTest {
	@Resource(name = "stockDao")
	private IStockDao stockDao;

	@Test
	public void getStockTest() {
		stockDao.getList();
	}
}