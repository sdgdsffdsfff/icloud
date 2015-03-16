package com.icloud.insurance.model;

// Generated 2015-3-16 16:47:03 by Hibernate Tools 3.4.0.CR1

/**
 * InsuranceObject generated by hbm2java
 */
public class InsuranceObject implements java.io.Serializable {

	private Integer id;
	private Integer insuranceId;
	private String insuranceKey;
	private Integer insuranceOrder;
	private String insuranceValue;

	public InsuranceObject() {
	}

	public InsuranceObject(Integer insuranceId, String insuranceKey,
			Integer insuranceOrder, String insuranceValue) {
		this.insuranceId = insuranceId;
		this.insuranceKey = insuranceKey;
		this.insuranceOrder = insuranceOrder;
		this.insuranceValue = insuranceValue;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceKey() {
		return this.insuranceKey;
	}

	public void setInsuranceKey(String insuranceKey) {
		this.insuranceKey = insuranceKey;
	}

	public Integer getInsuranceOrder() {
		return this.insuranceOrder;
	}

	public void setInsuranceOrder(Integer insuranceOrder) {
		this.insuranceOrder = insuranceOrder;
	}

	public String getInsuranceValue() {
		return this.insuranceValue;
	}

	public void setInsuranceValue(String insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

}
