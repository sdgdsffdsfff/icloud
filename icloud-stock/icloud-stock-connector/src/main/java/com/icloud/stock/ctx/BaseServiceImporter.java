package com.icloud.stock.ctx;

import org.slf4j.Logger;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.stock.service.ICategoryService;
import com.icloud.stock.service.ICategoryStockService;
import com.icloud.stock.service.IStockDateHistoryService;
import com.icloud.stock.service.IStockDivinePriceService;
import com.icloud.stock.service.IStockService;

public class BaseServiceImporter {
	protected static final Logger LOGGER = RequestIdentityLogger
			.getLogger(BaseServiceImporter.class);

	protected IStockService stockService;
	protected ICategoryService categoryService;
	protected ICategoryStockService categoryStockService;
	protected IStockDateHistoryService stockDateHistoryService;
	protected IStockDivinePriceService stockDivinePriceService;

	public BaseServiceImporter() {
		stockService = BeansUtil.getStockService();
		categoryService = BeansUtil.getCategoryService();
		categoryStockService = BeansUtil.getCategoryStockService();
		stockDateHistoryService = BeansUtil.getStockDateHistoryService();
		stockDivinePriceService = BeansUtil.getStockDivinePriceService();
	}
}
