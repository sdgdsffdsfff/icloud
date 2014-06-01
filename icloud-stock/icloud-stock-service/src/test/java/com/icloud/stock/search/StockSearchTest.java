package com.icloud.stock.search;

import java.util.List;

import org.junit.Test;

import com.icloud.front.stock.pojo.StockBean;
import com.icloud.stock.service.test.StockServiceTest;

public class StockSearchTest extends StockServiceTest {
	@Test
	public void searchStockNameTest() {
		List<StockBean> list = this.stockNameSearcher.search("创意", 10);
		for (StockBean bean : list) {
			System.out.println(bean.getStockName());
		}
	}
}
