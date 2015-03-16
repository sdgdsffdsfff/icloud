package com.icloud.insurance.util;

import com.icloud.framework.util.ICloudUtils;

public class InsuranceUtil {
	private static final String[] AGE_UNIT_VALUE = { "天", "月", "周岁" };
	private static final String[] SAFEGUARD_UNIT_VALUE = { "天", "个月", "年" };

	public static int convertYearToDay(int year) {
		return 365 * year;
	}

	/**
	 * 30天-5周岁 3-65周岁
	 * 
	 * @param fromDay
	 * @param toDay
	 * @return
	 */
	public static String convertTimeToAge(int fromDay, int toDay) {
		if (ICloudUtils.DEFAULT_INT_VALUE == toDay) {
			InsuranceTime insuranceTime = new InsuranceTime(fromDay);
			return insuranceTime.convertTimeToAge();
		}
		InsuranceTime fromInsuranceTime = new InsuranceTime(fromDay);
		InsuranceTime toInsuranceTime = new InsuranceTime(toDay);
		if (fromInsuranceTime.getUnit() != toInsuranceTime.getUnit()) {
			return fromInsuranceTime.convertTimeToAge() + " - "
					+ toInsuranceTime.convertTimeToAge();
		} else {
			return fromInsuranceTime.getBaseNumber() + " - "
					+ toInsuranceTime.convertTimeToAge();
		}
	}

	private static class InsuranceTime {
		private int baseNumber;
		private int unit;

		public InsuranceTime(int day) {
			if (day > 365) {
				unit = 2;
				baseNumber = day / 365;
			} else if (day > 30) {
				unit = 1;
				baseNumber = day / 30;
			} else {
				unit = 0;
				baseNumber = day;
			}
		}

		public String convertTimeToAge() {
			return baseNumber + AGE_UNIT_VALUE[unit];
		}

		public int getBaseNumber() {
			return baseNumber;
		}

		public int getUnit() {
			return unit;
		}

		public String convertTimeToSafeguardTime() {
			return baseNumber + SAFEGUARD_UNIT_VALUE[unit];
		}
	}

	public static String convertTimeToSafeguardTime(int day) {
		InsuranceTime insuranceTime = new InsuranceTime(day);
		return insuranceTime.convertTimeToSafeguardTime();
	}

	public static void main(String[] args) {
		System.out.println(convertYearToDay(2));
		System.out.println(convertTimeToAge(3, 105));
		System.out.println(convertTimeToSafeguardTime(105));
	}
}
