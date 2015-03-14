package com.icloud.insurance.domain.model;

import com.icloud.framework.util.ICloudUtils;

public class UnderwritingAge {
	private int startAge = ICloudUtils.DEFAULT_INT_VALUE;
	private int endAge = ICloudUtils.DEFAULT_INT_VALUE;

	public UnderwritingAge() {

	}

	public UnderwritingAge(int startAge, int endAge) {

		this.startAge = startAge;
		this.endAge = endAge;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	@Override
	public String toString() {
		return "UnderwritingAge [startAge=" + startAge + ", endAge=" + endAge
				+ "]";
	}

}
