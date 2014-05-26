package com.icloud.stock.importer.meta;

import java.util.List;

import com.icloud.framework.util.LogFileWriter;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.importer.handler.CompanyInfoHandler;
import com.icloud.stock.model.Stock;

public class XueQiuStockDetailImporter extends BaseServiceImporter {
	public void fetchData() {
		List<Stock> list = this.stockService.findAll();
		String filePath = "/home/jiangningcui/workspace/mygithub/icloud/icloud-stock/icloud-stock-connector/src/main/resources/data/xueqiu_detail.txt";
		LogFileWriter writer = new LogFileWriter(filePath);
		int count = 0;
		int size = list.size();
		for (Stock stock : list) {
			count++;
			System.out.println(stock.getStockAllCode() + " "
					+ stock.getStockName() + "  " + count + "/" + size);
			CompanyInfoHandler handler = new CompanyInfoHandler(stock);
			String line = handler.getContent();
			writer.apendln(line);
		}
		writer.close();
	}

	public static void main(String[] args) {
		XueQiuStockDetailImporter importer = new XueQiuStockDetailImporter();
		importer.fetchData();
	}
}
