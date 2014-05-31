package com.icloud.front.stock.bussiness;

import javax.annotation.Resource;

import com.icloud.stock.service.ICategoryService;
import com.icloud.stock.service.ICategoryStockService;
import com.icloud.stock.service.IStockDateHistoryService;
import com.icloud.stock.service.IStockDetailService;
import com.icloud.stock.service.IStockService;

public class BaseAction {
	@Resource(name = "stockService")
	protected IStockService stockService;
	@Resource(name = "categoryService")
	protected ICategoryService categoryService;
	@Resource(name = "categoryStockService")
	protected ICategoryStockService categoryStockService;

	@Resource(name = "stockDetailService")
	protected IStockDetailService stockDetailService;

	@Resource(name = "stockDateHistoryService")
	protected IStockDateHistoryService stockDateHistoryService;
}
