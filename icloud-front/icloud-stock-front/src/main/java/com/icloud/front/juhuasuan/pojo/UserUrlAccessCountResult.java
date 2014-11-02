package com.icloud.front.juhuasuan.pojo;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.bussiness.po.UserUrlAccessCountPo;

public class UserUrlAccessCountResult {
	private String chartName;
	private List<String> axisData;
	private List<String> countData;
	private List<String> allCountData;
	private List<String> validCountData;
	private List<String> allValidCountData;

	public static UserUrlAccessCountResult convertToUserUrlAccessCountResult(
			String chartName, List<UserUrlAccessCountPo> list) {
		if (ICloudUtils.isEmpty(list))
			return null;
		UserUrlAccessCountResult result = new UserUrlAccessCountResult();
		result.setChartName(chartName);

		List<String> axisData = new ArrayList<String>();
		List<String> countData = new ArrayList<String>();
		List<String> allCountData = new ArrayList<String>();
		List<String> validCountData = new ArrayList<String>();
		List<String> allValidCountData = new ArrayList<String>();

		for (int i = list.size() - 1; i >= 0; i--) {
			UserUrlAccessCountPo userUrlAccessCountPo = list.get(i);
			String axisDataStr = DateUtils.formatDate(
					userUrlAccessCountPo.getCreateTime(), "yyyy/MM/dd");

			String allCount = ICloudUtils.getDigitalString(userUrlAccessCountPo
					.getAllCount());
			String ount = ICloudUtils.getDigitalString(userUrlAccessCountPo
					.getCount());
			String validCount = ICloudUtils
					.getDigitalString(userUrlAccessCountPo.getValidCount());
			String allValidCount = ICloudUtils
					.getDigitalString(userUrlAccessCountPo.getAllValidCount());

			axisData.add(axisDataStr);
			countData.add(ount);
			allCountData.add(allCount);
			validCountData.add(validCount);
			allValidCountData.add(allValidCount);
		}

		result.setAxisData(axisData);
		result.setCountData(countData);
		result.setAllCountData(allCountData);
		result.setValidCountData(validCountData);
		result.setAllValidCountData(allValidCountData);
		return result;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public List<String> getAxisData() {
		return axisData;
	}

	public void setAxisData(List<String> axisData) {
		this.axisData = axisData;
	}

	public List<String> getCountData() {
		return countData;
	}

	public void setCountData(List<String> countData) {
		this.countData = countData;
	}

	public List<String> getAllCountData() {
		return allCountData;
	}

	public void setAllCountData(List<String> allCountData) {
		this.allCountData = allCountData;
	}

	public List<String> getValidCountData() {
		return validCountData;
	}

	public void setValidCountData(List<String> validCountData) {
		this.validCountData = validCountData;
	}

	public List<String> getAllValidCountData() {
		return allValidCountData;
	}

	public void setAllValidCountData(List<String> allValidCountData) {
		this.allValidCountData = allValidCountData;
	}

}
