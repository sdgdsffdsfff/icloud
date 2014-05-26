package com.icloud.stock.service;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.stock.model.Stock;

public interface IStockService extends ISqlBaseService<Stock> {

	Stock getByStockAllCode(String stockCode);

}
