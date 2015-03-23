package com.icloud.insurance.domain.aggregate;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.model.InsuranceAttribute;
import com.icloud.insurance.service.InsuranceAttributeService;

public class InsuranceCompanyAggregate extends AggregateRoot {
	private int companyId;
	private String companyName;
	private String uuid;
	private String imgUuid;

	private InsuranceAttributeService insuranceAttributeService;

	public static InsuranceCompanyAggregate convertInsuranceCompanyAggregate(
			InsuranceAttribute insuranceAttribute,
			InsuranceAttributeService insuranceAttributeService) {
		if (ICloudUtils.isNotNull(insuranceAttribute)) {
			InsuranceCompanyAggregate aggregate = new InsuranceCompanyAggregate(
					insuranceAttribute.getId(), insuranceAttributeService);
			aggregate.setCompanyId(insuranceAttribute.getId());
			aggregate.setCompanyName(insuranceAttribute.getAttributeName());
			aggregate.setImgUuid(insuranceAttribute.getDescription());
			aggregate.setUuid(insuranceAttribute.getUuid());
			return aggregate;
		}
		return null;
	}

	public static List<InsuranceCompanyAggregate> convertInsuranceCompanyAggregate(
			List<InsuranceAttribute> list,
			InsuranceAttributeService insuranceAttributeService) {
		if (!ICloudUtils.isEmpty(list)) {
			List<InsuranceCompanyAggregate> companies = new ArrayList<InsuranceCompanyAggregate>();
			for (InsuranceAttribute insuranceAttribute : list) {
				companies.add(convertInsuranceCompanyAggregate(
						insuranceAttribute, insuranceAttributeService));
			}
			return companies;
		}
		return null;
	}

	public InsuranceCompanyAggregate(int aggregateId,
			InsuranceAttributeService insuranceAttributeService) {
		super(aggregateId);
		this.insuranceAttributeService = insuranceAttributeService;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImgUuid() {
		return imgUuid;
	}

	public void setImgUuid(String imgUuid) {
		this.imgUuid = imgUuid;
	}

	public void updateAggregate() {
		this.insuranceAttributeService.saveOrUpdateCompany(this.companyId,
				this.companyName, this.imgUuid);
	}

}
