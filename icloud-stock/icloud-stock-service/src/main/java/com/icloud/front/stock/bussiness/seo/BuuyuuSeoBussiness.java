package com.icloud.front.stock.bussiness.seo;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.bussiness.BaseAction;
import com.icloud.stock.dict.SEOConstants;
import com.icloud.stock.model.Stock;

@Service("buuyuuSeoBussiness")
public class BuuyuuSeoBussiness extends BaseAction {

	public String getStockPageTitle(Stock stock) {
		String title = SEOConstants.STOCK_TITLE;
		if (ICloudUtils.isNotNull(stock)) {
			title = stock.getStockName() + " "
					+ ICloudUtils.getDigitalString(stock.getCurrentPrice(), 2)
					+ "(" + ICloudUtils.getDigitalString(stock.getPercent(), 2)
					+ ") (" + stock.getStockAllCode()
					+ ")  股票股价,行情,新闻,财报,数据 - 必有网站";
		}
		return title;
	}

	public String getStockPageKeywords(Stock stock) {
		String keywords = SEOConstants.STOCK_KEYWORDS;
		if (ICloudUtils.isNotNull(stock)) {
			keywords = stock.getStockName() + "," + stock.getStockCode()
					+ ",必有股票,必有,必有财经," + stock.getStockName() + "行情,"
					+ stock.getStockName() + "交易," + stock.getStockName()
					+ "新闻," + stock.getStockName() + "资讯,"
					+ stock.getStockName() + "价格," + stock.getStockName()
					+ "资金流向," + stock.getStockName() + "实时行情,"
					+ stock.getStockName() + "研究报告," + stock.getStockName()
					+ "点评," + stock.getStockName() + "讨论,"
					+ stock.getStockName() + "财报," + stock.getStockName()
					+ "财务分析";
		}
		return keywords;
	}

	public String getStockPageDescription(Stock stock) {
		String description = SEOConstants.STOCK_DESCRIPTION;
		if (ICloudUtils.isNotNull(stock)) {
			description = stock.getStockName() + "(" + stock.getStockAllCode()
					+ "的股票股价,行情,新闻,财报,数据," + stock.getStockName() + "("
					+ stock.getStockAllCode() + ")," + stock.getStockCode()
					+ ",必有,必有财经," + stock.getStockName() + "行情,"
					+ stock.getStockName() + "交易," + stock.getStockName()
					+ "新闻," + stock.getStockName() + "资讯,"
					+ stock.getStockName() + "价格," + stock.getStockName()
					+ "资金流向," + stock.getStockName() + "实时行情,"
					+ stock.getStockName() + "研究报告," + stock.getStockName()
					+ "点评," + stock.getStockName() + "讨论,"
					+ stock.getStockName() + "财报," + stock.getStockName()
					+ "财务分析";
		}
		return description;
	}

	public void setSeoInStockDetail(Stock stock, ModelAndView model) {
		if (ICloudUtils.isNotNull(stock) && ICloudUtils.isNotNull(model)) {
			model.addObject("stockPageTitle", getStockPageTitle(stock));
			model.addObject("stockPageKeywords", getStockPageKeywords(stock));
			model.addObject("stockPageDescription",
					getStockPageDescription(stock));
		}
	}

}
