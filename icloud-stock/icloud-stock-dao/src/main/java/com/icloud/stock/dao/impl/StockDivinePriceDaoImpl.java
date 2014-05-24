package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IStockDivinePriceDao;
import com.icloud.stock.model.StockDivinePrice;

@Repository("stockDivinePriceDao")
public class StockDivinePriceDaoImpl extends StockBaseDaoImpl<StockDivinePrice>
		implements IStockDivinePriceDao {
}
