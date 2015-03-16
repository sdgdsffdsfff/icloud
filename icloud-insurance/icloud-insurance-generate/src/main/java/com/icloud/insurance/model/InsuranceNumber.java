package com.icloud.insurance.model;

// Generated 2015-3-16 16:47:03 by Hibernate Tools 3.4.0.CR1

/**
 * InsuranceNumber generated by hbm2java
 */
public class InsuranceNumber implements java.io.Serializable {

	private Integer id;
	private Integer insuranceOrder;
	private Integer insuranceValue;
	private String insuranceDescription;
	private Integer insuranceId;
	private Integer insuranceKey;

	public InsuranceNumber() {
	}

	public InsuranceNumber(Integer insuranceOrder, Integer insuranceValue,
			String insuranceDescription, Integer insuranceId,
			Integer insuranceKey) {
		this.insuranceOrder = insuranceOrder;
		this.insuranceValue = insuranceValue;
		this.insuranceDescription = insuranceDescription;
		this.insuranceId = insuranceId;
		this.insuranceKey = insuranceKey;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInsuranceOrder() {
		return this.insuranceOrder;
	}

	public void setInsuranceOrder(Integer insuranceOrder) {
		this.insuranceOrder = insuranceOrder;
	}

	public Integer getInsuranceValue() {
		return this.insuranceValue;
	}

	public void setInsuranceValue(Integer insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public String getInsuranceDescription() {
		return this.insuranceDescription;
	}

	public void setInsuranceDescription(String insuranceDescription) {
		this.insuranceDescription = insuranceDescription;
	}

	public Integer getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getInsuranceKey() {
		return this.insuranceKey;
	}

	public void setInsuranceKey(Integer insuranceKey) {
		this.insuranceKey = insuranceKey;
	}

}
