package com.icloud.stock.app;

import org.slf4j.Logger;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.analysis.StockAnalysis;
import com.icloud.stock.importer.data.StockHistoryDataImporter;

public class AppMain {
	protected static final Logger LOGGER = RequestIdentityLogger
			.getLogger(AppMain.class);
	public static final String ANALYSIS = "analysis";
	public static final String FETCHHISTORY = "fetchHistory";
	public static final String ALL = "all";

	public static void pringUsge() {
		LOGGER.info("error: ");
		LOGGER.info("tips : ");
		LOGGER.info("appName analysis|fetchHistory|all");
	}

	private static void appStart(String[] args) {
		if (!ICloudUtils.isNotNull(args) || args.length != 1) {
			pringUsge();
			return;
		}
		String common = args[0];
		if (common.equalsIgnoreCase(FETCHHISTORY)
				|| common.equalsIgnoreCase(ALL)) {
			StockHistoryDataImporter importer = new StockHistoryDataImporter();
			importer.loadData();
			LOGGER.info("第一轮结束");
			importer.loadData();
			LOGGER.info("第二轮结束");
		}
		if (common.equalsIgnoreCase(ANALYSIS) || common.equalsIgnoreCase(ALL)) {
			StockAnalysis stockAnalysis = new StockAnalysis();
			stockAnalysis.singleAnalysis();
		}
		LOGGER.info("all is ok");
	}

	public static void main(String[] args) {
		appStart(args);
	}

}
