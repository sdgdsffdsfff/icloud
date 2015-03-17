package com.icloud.insurance.domain.model;

public class InsuranceBaseInfo {
	private String safeguardTimeDesc = null;
	private String suitePeopleDesc = null;

	public String getSafeguardTimeDesc() {
		return safeguardTimeDesc;
	}

	public void setSafeguardTimeDesc(String safeguardTimeDesc) {
		this.safeguardTimeDesc = safeguardTimeDesc;
	}

	public String getSuitePeopleDesc() {
		return suitePeopleDesc;
	}

	public void setSuitePeopleDesc(String suitePeopleDesc) {
		this.suitePeopleDesc = suitePeopleDesc;
	}

	@Override
	public String toString() {
		return "InsuranceBaseInfo [safeguardTimeDesc=" + safeguardTimeDesc
				+ ", suitePeopleDesc=" + suitePeopleDesc + "]";
	}

	
}
