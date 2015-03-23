package com.icloud.insurance.domain.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.aggregate.InsuranceAggregate;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.InsuranceProductService;

@Service("insuranceAggregateService")
public class InsuranceAggregateService extends BaseService {
	@Resource(name = "insuranceProductService")
	protected InsuranceProductService insuranceProductService;

	/**
	 * 存储元数据
	 * 
	 * @param product
	 * @return
	 */
	public InsuranceAggregate saveInsuranceProduct(InsuranceProduct product) {
		if (!ICloudUtils.isNotNull(product)) {
			return null;
		}
		product = this.insuranceProductService.save(product);
		return InsuranceAggregate
				.convertInsuranceAggregateFromInsuranceProduct(product,
						insuranceNumberService, insuranceObjectService);
	}

	public InsuranceAggregate getInsuranceAggregateById(int id,
			boolean lazyLoading) {
		InsuranceProduct product = insuranceProductService.getById(id);
		if (ICloudUtils.isNotNull(product)) {
			return InsuranceAggregate
					.convertInsuranceAggregateFromInsuranceProduct(product,
							insuranceNumberService, insuranceObjectService,
							lazyLoading);
		}
		return null;
	}

	public InsuranceAggregate getInsuranceAggregateById(int id) {
		return getInsuranceAggregateById(id, true);
	}

	public void deleteAggregate(int id) {
		InsuranceAggregate insuranceAggregate = getInsuranceAggregateById(id);
		if (ICloudUtils.isNotNull(insuranceAggregate)) {
			insuranceAggregate.deletAllAttribute();
			InsuranceProduct product = insuranceProductService.getById(id);
			this.insuranceProductService.delete(product);
		}

	}
}
