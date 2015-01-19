package com.domain.myapp.model;

public class CustomerAggregate {
	public static final String NAME = "name";
	public static final String YEAR = "year";
	private String aggreagetRootId;
	private String customerName;
	private int customerYear;

	public String getAggreagetRootId() {
		return aggreagetRootId;
	}

	public void setAggreagetRootId(String aggreagetRootId) {
		this.aggreagetRootId = aggreagetRootId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerYear() {
		return customerYear;
	}

	public void setCustomerYear(int customerYear) {
		this.customerYear = customerYear;
	}

	@Override
	public String toString() {
		return "CustomerAggregate [aggreagetRootId=" + aggreagetRootId
				+ ", customerName=" + customerName + ", customerYear="
				+ customerYear + "]";
	}

}
