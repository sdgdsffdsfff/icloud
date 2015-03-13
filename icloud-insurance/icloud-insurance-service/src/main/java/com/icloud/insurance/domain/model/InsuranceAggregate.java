package com.icloud.insurance.domain.model;

import java.util.Date;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.model.InsuranceProduct;

public class InsuranceAggregate {
	private Integer id;
	private String insuranceName;
	private String insuranceCompany;
	private String simpleDescription;
	private Integer safeguardTime;
	private Date crateTime;
	private Date lastUpdateTime;
	private Integer lastUpdateUserId;
	private String lastUpdateUserName;

	public InsuranceAggregate() {
	}

	public static InsuranceAggregate convertInsuranceAggregateFromInsuranceProduct(
			InsuranceProduct product) {
		InsuranceAggregate aggreate = new InsuranceAggregate();
		aggreate = ICloudUtils.dozerCopy(aggreate, product);
		return aggreate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getSimpleDescription() {
		return simpleDescription;
	}

	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}

	public Integer getSafeguardTime() {
		return safeguardTime;
	}

	public void setSafeguardTime(Integer safeguardTime) {
		this.safeguardTime = safeguardTime;
	}

	public Date getCrateTime() {
		return crateTime;
	}

	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public void setLastUpdateUserName(String lastUpdateUserName) {
		this.lastUpdateUserName = lastUpdateUserName;
	}

	@Override
	public String toString() {
		return "InsuranceAggregate [id=" + id + ", insuranceName="
				+ insuranceName + ", insuranceCompany=" + insuranceCompany
				+ ", simpleDescription=" + simpleDescription
				+ ", safeguardTime=" + safeguardTime + ", crateTime="
				+ crateTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", lastUpdateUserId=" + lastUpdateUserId
				+ ", lastUpdateUserName=" + lastUpdateUserName + "]";
	}

}
