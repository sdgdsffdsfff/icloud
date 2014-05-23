package com.icloud.stock.connector.handler.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StopWatch;

import com.icloud.stock.connector.handler.StockHandler;
import com.icloud.stock.connector.parser.Parser;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.constant.StockConstants.StockLocation;
import com.icloud.framework.net.http.TZHttpClient;

public class DownLoadCVSHandler extends
		StockHandler<ArrayList<StockDateHistory>> {
	private String code;
	private int stock_id;
	private String type;

	@Override
	public void init() {
		/**
		 * 获得泛型参数类型
		 */
		LOGGER.info("此处千万不要删除哦");
	}

	private DownLoadCVSHandler(String url, Map<String, String> params)
			throws NoSuchFieldException, SecurityException,
			NoSuchMethodException {
		super(url, params);
	}

	private DownLoadCVSHandler(String url) throws NoSuchFieldException,
			SecurityException, NoSuchMethodException {
		super(url);
	}

	/**
	 * 所有数据
	 *
	 * @param code
	 * @param location
	 * @param stock_id
	 */
	public DownLoadCVSHandler(String code, StockLocation location, int stock_id) {
		type = "sz";
		if (location == StockLocation.SZX) {
			type = "sz";
		} else if (location == StockLocation.SHA) {
			type = "ss";
		}
		this.code = code;
		this.url = "http://table.finance.yahoo.com/table.csv?s=" + code + "."
				+ type;
		this.stock_id = stock_id;
	}

	/**
	 * 某个时间段的数据
	 *
	 * @param code
	 * @param location
	 * @param stock_id
	 */
	public DownLoadCVSHandler(String code, StockLocation location,
			int stock_id, int a, int b, int c, int d, int e, int f) {
		this(code, location, stock_id);
		a = a - 1;
		d = d - 1;
		this.url = "http://ichart.yahoo.com/table.csv?s=" + code + "." + type + "&a="
				+ a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f
				+ "&g=d";

	}

	@Override
	public ArrayList<StockDateHistory> getHttpData() {
		StopWatch stopwatch = new StopWatch("获得cvs信息");
		stopwatch.start("调用http接口");
		Map<String, String> params = new HashMap<String, String>();
		LOGGER.info(url);
		TZHttpClient client = new TZHttpClient(url, params);
		InputStream inputStreamCVS = client.downCVSFile();
		stopwatch.stop();
		stopwatch.start("解析数据");
		Parser<ArrayList<StockDateHistory>> parser = (Parser<ArrayList<StockDateHistory>>) factory
				.getParser((new ArrayList<StockDateHistory>()).getClass());
		Object[] objects = { inputStreamCVS, code, stock_id };
		ArrayList<StockDateHistory> result = parser.parse(null, objects);
		stopwatch.stop();
		LOGGER.info("{}:{}", stopwatch.getLastTaskName(), stopwatch.toString());
		return result;
	}

	@Override
	public void apply() {
		// TODO Auto-generated method stub

	}
}
