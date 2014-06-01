package com.icloud.stock.search.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.bussiness.BaseAction;
import com.icloud.front.stock.pojo.StockBean;
import com.icloud.stock.search.AutoCompleteBean;
import com.icloud.stock.search.AutoCompleteUtil;
import com.icloud.stock.search.StockIndexconstants;
import com.icloud.stock.search.StockNameIndexer;
import com.icloud.stock.search.TopsAutoCompleteSearchServer;

@Service("stockNameSearcher")
public class StockNameSearcher extends BaseAction {
	private String indexPath = PropertiesUtil.getProperty(
			"properties/stocksearcher.properties", "icloud.stock.indexer.path");

	private boolean isOk = false;

	private boolean haveBuilderIndex() {
		if (!isOk) {
			File file = new File(indexPath);
			if (file.exists() && file.list().length > 1) {
				isOk = true;
			}
		}
		return isOk;
	}

	private synchronized void initIndexer() {
		if (haveBuilderIndex()) {

		} else {
			StockNameIndexer indexer = new StockNameIndexer("stockName",
					this.stockService);
			indexer.makeIndex(indexPath, null, true);
			isOk = true;
		}
	}

	public List<StockBean> search(String query, int limit) {
		if (!haveBuilderIndex()) {
			initIndexer();
		}
		List<AutoCompleteBean> list = TopsAutoCompleteSearchServer
				.getInstance().autoComplete(query, limit,
						StockIndexconstants.META_STOCK_NAME, indexPath);
		if (!ICloudUtils.isEmpty(list)) {
			return AutoCompleteUtil
					.convertAutoCompleteBeanToStockBeanList(list);
		} else {
			return AutoCompleteUtil
					.convertStockToStockBeanList(this.stockService.findAll(0,
							limit));
		}
	}

}
