package com.icloud.stock.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.joda.time.DateTime;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.search.autocomplete.index.AutoCompleteBuilder;
import com.icloud.stock.model.Stock;
import com.icloud.stock.service.IStockService;

public class StockNameIndexer extends AutoCompleteBuilder {
	private IStockService stockService;

	private StockNameIndexer(String indexName) {
		super(indexName);
	}

	public StockNameIndexer(String indexName, IStockService stockService) {
		this(indexName);
		this.stockService = stockService;
	}

	@Override
	public boolean makeIndex(String indexPath, DateTime startTime, boolean isAll) {
		init(indexPath, true);
		List<Stock> list = this.stockService.findAll();
		if (!ICloudUtils.isEmpty(list)) {
			for (Stock stock : list) {
				addStockToIndex(stock);
			}
		}
		logger.info("end to index, index " + count + " documents");
		closeAndOptimize();
		return true;
	}

	private void addStockToIndex(Stock stock) {
		if (!ICloudUtils.isNotNull(stock)) {
			return;
		}
		Set<String> set = new HashSet<String>();
		List<Field> metaFieldList = getAirLineFieldList(stock);
		generateDocument(stock.getStockCode(), stock.getId() + "", 5.0f, set,
				metaFieldList);
		generateDocument(stock.getStockAllCode(), stock.getId() + "", 1.0f,
				set, metaFieldList);
		generateDocument(stock.getStockName(), stock.getId() + "", 1.0f, set,
				metaFieldList);
	}

	private List<Field> getAirLineFieldList(Stock stock) {

		List<Field> list = new ArrayList<Field>();
		if (stock == null)
			return list;
		this.addFieldToList(
				list,
				getField(StockIndexconstants.STOCK_ID,
						stock.getId().toString(), Store.YES, Index.NO, 1.0f));
		this.addFieldToList(
				list,
				getField(StockIndexconstants.STOCK_CODE, stock.getStockCode(),
						Store.YES, Index.NO, 1.0f));
		this.addFieldToList(
				list,
				getField(StockIndexconstants.STOCK_NAME, stock.getStockName(),
						Store.YES, Index.NO, 1.0f));
		this.addFieldToList(
				list,
				getField(StockIndexconstants.STOCK_ALLCODE,
						stock.getStockAllCode(), Store.YES, Index.NO, 1.0f));
		this.addFieldToList(
				list,
				getField(StockIndexconstants.META_TYPE,
						StockIndexconstants.META_STOCK_NAME + "", Store.YES,
						Index.NOT_ANALYZED, 1.0f));
		return list;
	}

}
