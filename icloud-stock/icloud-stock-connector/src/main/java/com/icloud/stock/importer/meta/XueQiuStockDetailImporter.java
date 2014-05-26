package com.icloud.stock.importer.meta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.icloud.framework.file.TextFile;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.LogFileWriter;
import com.icloud.stock.ctx.BaseServiceImporter;
import com.icloud.stock.importer.handler.CompanyInfoHandler;
import com.icloud.stock.importer.handler.XueqiuStockDetail;
import com.icloud.stock.model.Stock;

public class XueQiuStockDetailImporter extends BaseServiceImporter {
	String filePath = "/home/jiangningcui/workspace/mygithub/icloud/icloud-stock/icloud-stock-connector/src/main/resources/data/xueqiu_detail.txt";
	Set<String> set = new HashSet<String>();

	public void fetchData() {
		List<Stock> list = this.stockService.findAll();
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

	public void updateDB() {
		TextFile file = new TextFile(filePath);
		Gson gson = new Gson();
		for (String l : file) {
			XueqiuStockDetail detail = gson
					.fromJson(l, XueqiuStockDetail.class);
			long flowStock = getNum(detail.getFlowStock());
			long totalStock = getNum(detail.getTotalStock());
			long totalMoney = getNum(detail.getTotalMoney());

			String companyInfoWork = detail.getCompanyInfoWork();
			String detailContent = detail.getDetailContent();
			String title = detail.getTitle();

			String stockTitle = detail.getStockTitle();

			String stockAllCode = detail.getStockCode();

			Stock stock = this.stockService.getByStockAllCode(stockAllCode);
			System.out.println(stock.getStockAllCode() + "  " + stock.getId()
					+ " " + stockAllCode);
		}
	}

	public long getNum(String numStr) {
		if (ICloudUtils.isNotNull(numStr)) {
			numStr = numStr.trim();
			if (numStr.equalsIgnoreCase("-")) {
			} else {
				long value = 0;
				if (numStr.endsWith("万")) {
					numStr = numStr.substring(0, numStr.length() - 1);
					value = (long) (Double.parseDouble(numStr) * 10000);

				} else if (numStr.endsWith("亿")) {
					numStr = numStr.substring(0, numStr.length() - 1);
					value = (long) (Double.parseDouble(numStr) * 10000 * 10000);

				} else {
					value = (long) Double.parseDouble(numStr);
				}
				return value;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		XueQiuStockDetailImporter importer = new XueQiuStockDetailImporter();
		// importer.fetchData();
		importer.updateDB();

	}
}
