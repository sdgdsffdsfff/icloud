package com.icloud.stock.importer.handler;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.google.gson.Gson;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.http.HtmlUnit;
import com.icloud.stock.model.Stock;

public class CompanyInfoHandler {
	static HtmlUnit client = new HtmlUnit(false);
	private Stock stock;

	private XueqiuStockDetail detail;

	public CompanyInfoHandler(Stock stock) {
		this.stock = stock;
		try {
			fetch();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CompanyInfoHandler(String code, String title) {
		stock = new Stock();
		stock.setStockAllCode(code);
		stock.setStockName(title);
		try {
			fetch();
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fetch() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		detail = new XueqiuStockDetail();
		detail.setStockCode(stock.getStockAllCode());
		detail.setStockTitle(stock.getStockName());

		String url = "http://xueqiu.com/S/" + stock.getStockAllCode();
		String content = client.getContentAsString(url);
		/**
		 * 处理内容
		 */
		processDetailContent(content);
		/**
		 * 处理业务
		 */
		processDetailCompanyWork(content);
		/**
		 * 获得其他信息
		 *
		 */
		processDetail(content);

	}

	private void processDetail(String content) {
		if (!ICloudUtils.isNotNull(content))
			return;
		String indexStr = ".stockQuote";
		if (content.indexOf(indexStr) != -1)
			content = content.substring(content.indexOf(indexStr));
		String title = ICloudUtils.getSubStringFromContent(content,
				"<strong title=\"\" class=\"stockName\">", "</strong>");
		detail.setTitle(title);
		String totalMoney = ICloudUtils.getSubStringFromContent(content,
				"总市值：<span>", "</span>");
		detail.setTotalMoney(totalMoney);
		String totalStock = ICloudUtils.getSubStringFromContent(content,
				"<td>总股本：<span>", "</span>");
		detail.setTotalStock(totalStock);
		String flowStock = ICloudUtils.getSubStringFromContent(content,
				"流通股本：<span>", "</span>");
		detail.setFlowStock(flowStock);
	}

	private void processDetailCompanyWork(String content) {
		if (content != null) {
			String startIndexStr = "<p class=\"companyInfo\"><strong class=\"title\">业务:</strong>";
			String endIndexStr = "</p>";
			String companyInfoWork = ICloudUtils.getSubStringFromContent(
					content, startIndexStr, endIndexStr);
			detail.setCompanyInfoWork(companyInfoWork);
		}
	}

	private void processDetailContent(String content) {
		if (content != null) {
			String startIndexStr = "<p class=\"companyInfo detailContent\"><strong class=\"title\">简介:</strong>";
			String endIndexStr = "</p>";
			String detailContent = ICloudUtils.getSubStringFromContent(content,
					startIndexStr, endIndexStr);
			if (ICloudUtils.isNotNull(detailContent)) {

				if (detailContent.indexOf("<span") != -1) {
					detailContent = detailContent.substring(0,
							detailContent.indexOf("<span"));
				}
			}
			detail.setDetailContent(detailContent);
		}
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getContent() {
		Gson gson = new Gson();
		return gson.toJson(detail);
	}

	public static void main(String[] args) {
		CompanyInfoHandler handler = new CompanyInfoHandler("SH600714", "金瑞矿业");
		System.out.println(handler.getContent());
	}

}
