package com.icloud.stock.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.stock.bussiness.PersonService;
import com.icloud.stock.model.Category;
import com.icloud.stock.model.StockDetail;
import com.icloud.stock.search.service.StockNameSearcher;
import com.icloud.stock.service.ICategoryService;
import com.icloud.stock.service.ICategoryStockService;
import com.icloud.stock.service.IStockDetailService;
import com.icloud.stock.service.IStockService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/icloud-stock-service-ctx-min.xml" })
public class StockServiceTest {
	@Resource(name = "stockService")
	private IStockService stockService;

	@Resource(name = "personService")
	private PersonService personService;

	@Resource(name = "categoryStockService")
	private ICategoryStockService catgoryStockService;

	@Resource(name = "categoryService")
	private ICategoryService catgoryService;

	@Resource(name = "stockDetailService")
	private IStockDetailService stockDetailService;

	@Resource(name = "stockNameSearcher")
	protected StockNameSearcher stockNameSearcher;

	@Test
	public void getAllStock() {
		// List<Stock> list = this.stockService.findAll(0, 100);
		// for (Stock stock : list) {
		// System.out.println(stock.getStockAllCode());
		// }
		// System.out.println("ok");
		Category category = this.catgoryService.getCategory("创业板", "base");
		System.out.println(category.getCategoryRank());
	}

	@Test
	public void getStockDetail() {
		StockDetail stockDetail = this.stockDetailService.getById(2);
		System.out.println(stockDetail.getStockCode());
		stockDetail = stockDetailService.getStockByStockCode(stockDetail
				.getStockCode());
		System.out.println(stockDetail.getStockCode());
		stockDetail = stockDetailService.getStockByStockId(stockDetail
				.getStockId());
		System.out.println(stockDetail.getStockCode());
		System.out.println(stockDetail.getDetailContent());

	}
}
