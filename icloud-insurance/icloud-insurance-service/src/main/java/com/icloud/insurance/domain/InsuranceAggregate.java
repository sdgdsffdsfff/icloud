package com.icloud.insurance.domain;

import java.util.Date;

import org.dozer.DozerBeanMapper;

import com.cninfo.shtb.member.domain.AccountAggregate;
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

	public InsuranceAggregate(InsuranceProduct product) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		AccountAggregate accountAggregate = (AccountAggregate) mapper.map(
				account, AccountAggregate.class);
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

}
