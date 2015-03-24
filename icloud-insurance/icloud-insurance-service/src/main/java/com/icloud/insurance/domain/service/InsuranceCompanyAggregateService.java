package com.icloud.insurance.domain.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.aggregate.InsuranceCompanyAggregate;
import com.icloud.insurance.model.InsuranceAttribute;
import com.icloud.insurance.model.MediaFile;

@Service("insuranceCompanyAggregateService")
public class InsuranceCompanyAggregateService extends BaseService {
	public InsuranceCompanyAggregate getInsuranceCompanyAggregate(
			int aggregateId) {
		InsuranceAttribute insuranceAttribute = this.insuranceAttributeService.getInsuranceCompany(aggregateId);
		return InsuranceCompanyAggregate.convertInsuranceCompanyAggregate(
				insuranceAttribute, this.insuranceAttributeService);
	}

	public List<InsuranceCompanyAggregate> getAllInsuranceCompany() {
		List<InsuranceAttribute> list = this.insuranceAttributeService
				.findAllInsuranceCompany();
		return InsuranceCompanyAggregate.convertInsuranceCompanyAggregate(list,
				this.insuranceAttributeService);
	}

	public InsuranceCompanyAggregate saveCompanyAggregate(String companyName,
			File imgFile) {
		MediaFile mediaFile = mediaFileService.save(imgFile);
		return this.saveCompanyAggregate(companyName, mediaFile);
	}

	public InsuranceCompanyAggregate saveCompanyAggregate(String companyName,
			MediaFile mediaFile) {
		if (ICloudUtils.isNotNull(companyName)
				&& ICloudUtils.isNotNull(mediaFile)) {
			InsuranceAttribute insuranceAttribute = insuranceAttributeService
					.saveCompany(companyName, mediaFile.getFileHashId());
			return InsuranceCompanyAggregate.convertInsuranceCompanyAggregate(
					insuranceAttribute, this.insuranceAttributeService);
		}
		return null;
	}
}
