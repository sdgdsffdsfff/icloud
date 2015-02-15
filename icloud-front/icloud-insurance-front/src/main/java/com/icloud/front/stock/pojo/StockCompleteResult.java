package com.icloud.front.stock.pojo;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;

public class StockCompleteResult {
	private List<StockCompleteBean> data;

	public List<StockCompleteBean> getData() {
		return data;
	}

	public void setData(List<StockCompleteBean> data) {
		this.data = data;
	}

	public static StockCompleteResult convertToStockCompleteResult(
			List<StockBean> list) {
		if (!ICloudUtils.isEmpty(list)) {
			StockCompleteResult result = new StockCompleteResult();
			List<StockCompleteBean> data = new ArrayList<StockCompleteBean>();
			result.setData(data);

			for (StockBean stockBean : list) {
				StockCompleteBean stockCompleteBean = new StockCompleteBean();
				stockCompleteBean.setTitle(stockBean.getStockName() + "("
						+ stockBean.getStockAllCode() + ")");
				stockCompleteBean.setResult(stockBean.getStockCode());
				data.add(stockCompleteBean);
			}
			return result;
		}
		return null;
	}
}
