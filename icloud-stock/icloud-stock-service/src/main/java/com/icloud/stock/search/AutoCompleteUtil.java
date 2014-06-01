package com.icloud.stock.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.pojo.StockBean;
import com.icloud.stock.model.Stock;

public class AutoCompleteUtil {
	public static List<StockBean> convertAutoCompleteBeanToStockBeanList(
			List<AutoCompleteBean> list) {
		List<StockBean> result = new ArrayList<StockBean>();
		if (!ICloudUtils.isEmpty(list)) {
			for (AutoCompleteBean bean : list) {
				Document doc = bean.getDoc();
				if (ICloudUtils.isNotNull(doc)) {
					StockBean stockBean = new StockBean();
					stockBean.setStockId(doc.get(StockIndexconstants.STOCK_ID));
					stockBean.setStockCode(doc
							.get(StockIndexconstants.STOCK_CODE));
					stockBean.setStockAllCode(doc
							.get(StockIndexconstants.STOCK_ALLCODE));
					stockBean.setStockName(doc
							.get(StockIndexconstants.STOCK_NAME));
					result.add(stockBean);
				}
			}
		}
		return result;
	}

	public static List<StockBean> convertStockToStockBeanList(List<Stock> list) {
		List<StockBean> result = new ArrayList<StockBean>();
		if (!ICloudUtils.isEmpty(list)) {
			for (Stock bean : list) {
				if (ICloudUtils.isNotNull(bean)) {
					StockBean stockBean = new StockBean();
					stockBean.setStockId(bean.getId() + "");
					stockBean.setStockCode(bean.getStockCode());
					stockBean.setStockAllCode(bean.getStockAllCode());
					stockBean.setStockName(bean.getStockName());
					result.add(stockBean);
				}
			}
		}
		return result;
	}

}
