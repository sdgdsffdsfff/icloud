package com.icloud.stock.ctx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icloud.front.Paper.bussiness.PaperBussiness;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanStatBusiness;
import com.icloud.front.marketing.bussiness.MarketingBusiness;
import com.icloud.stock.service.ICategoryService;
import com.icloud.stock.service.ICategoryStockService;
import com.icloud.stock.service.IStockDateHistoryService;
import com.icloud.stock.service.IStockDetailService;
import com.icloud.stock.service.IStockDivinePriceService;
import com.icloud.stock.service.IStockService;

public class BeansUtil {
	private static ApplicationContext app;

	static {
		app = new ClassPathXmlApplicationContext(
				"spring/icloud-stock-service-ctx-min.xml");

	}

	public static IStockService getStockService() {
		return (IStockService) app.getBean("stockService");
	}

	public static ICategoryService getCategoryService() {
		return (ICategoryService) app.getBean("categoryService");
	}

	public static ICategoryStockService getCategoryStockService() {
		return (ICategoryStockService) app.getBean("categoryStockService");
	}

	public static IStockDateHistoryService getStockDateHistoryService() {
		return (IStockDateHistoryService) app
				.getBean("stockDateHistoryService");
	}

	public static IStockDivinePriceService getStockDivinePriceService() {
		return (IStockDivinePriceService) app
				.getBean("stockDivinePriceService");
	}

	public static IStockDetailService getStockDetailService() {
		return (IStockDetailService) app.getBean("stockDetailService");
	}

	public static JuhuasuanStatBusiness getJuhuasuanStatBusiness() {
		return (JuhuasuanStatBusiness) app.getBean("juhuasuanStatBusiness");
	}

	public static MarketingBusiness getMarketingBusiness() {
		return (MarketingBusiness) app.getBean("marketingBusiness");
	}

	public static PaperBussiness getPaperBussiness() {
		return (PaperBussiness) app.getBean("paperBussiness");
	}

}
