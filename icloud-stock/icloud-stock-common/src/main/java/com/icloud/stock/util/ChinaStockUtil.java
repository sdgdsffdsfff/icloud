package com.icloud.stock.util;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.icloud.framework.core.time.DateTimeUtil;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.bean.StockPrice;

public class ChinaStockUtil {
	/**
	 * 获得当前上一个股票工作日 规则: 1-5: 如果当前时间为晚上20:00点以后,那么返回当天,否则,返回前一天 6,7:返回周五时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastWorkDate(Date date) {
		if (!ICloudUtils.isNotNull(date)) {
			return null;
		}
		int d = DateTimeUtil.getDayOfWeek(date);
		if (d > 5) {
			date = DateUtils.addDays(date, (5 - d));
		} else {
			// if (date.getHours() >= 20) {
			//
			// } else {
			d = d - 1;
			if (d > 0) {
				date = DateUtils.addDays(date, -1);
			}else{
				date = DateUtils.addDays(date, -3);
			}
			// }
		}
		return date;
	}

	public static Date getNexStockDate(Date date) {
		if (!ICloudUtils.isNotNull(date)) {
			date = new Date();
		}
		date = DateUtils.addDays(date, 1);
		int d = DateTimeUtil.getDayOfWeek(date);
		if (d > 5) {
			date = DateUtils.addDays(date, (8 - d));
		}
		return date;
	}

	public static void main(String[] args) {
		Date date = new Date();
		// System.out.println(getLastWorkDate(date));
		date = DateUtils.addDays(date, 1);
		System.out.println("date : " + date);
		// System.out.println(date);
		// System.out.println(getLastWorkDate(date));

		System.out.println(getNexStockDate(date));

		// System.out.println(getDayOfWeek(date));
		// date = DateUtils.addDays(date, -5);
		//
		// System.out.println("date = " + d);
		// if (d > 5) {
		// System.out.println(date + " " + (d - 5));
		//
		// }
		// System.out.println(getDayOfWeek(date));
	}

}
