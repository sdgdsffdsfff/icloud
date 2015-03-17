package com.icloud.insurance.domain.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.model.InsuranceAggregate;
import com.icloud.insurance.model.InsuranceProduct;
import com.icloud.insurance.service.InsuranceNumberService;
import com.icloud.insurance.service.InsuranceObjectService;
import com.icloud.insurance.service.InsuranceProductService;

@Service("insuranceAggregateService")
public class InsuranceAggregateService {
	@Resource(name = "insuranceProductService")
	protected InsuranceProductService insuranceProductService;
	@Resource(name = "insuranceNumberService")
	protected InsuranceNumberService insuranceNumberService;
	@Resource(name = "insuranceObjectService")
	protected InsuranceObjectService insuranceObjectService;

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
}