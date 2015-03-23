package com.icloud.insurance.domain.service;

import javax.annotation.Resource;

import com.icloud.insurance.service.InsuranceAttributeService;
import com.icloud.insurance.service.InsuranceNumberService;
import com.icloud.insurance.service.InsuranceObjectService;
import com.icloud.insurance.service.MediaFileService;

public class BaseService {
	@Resource(name = "insuranceAttributeService")
	protected InsuranceAttributeService insuranceAttributeService;
	@Resource(name = "insuranceNumberService")
	protected InsuranceNumberService insuranceNumberService;
	@Resource(name = "insuranceObjectService")
	protected InsuranceObjectService insuranceObjectService;
	@Resource(name = "mediaFileService")
	protected MediaFileService mediaFileService;

}
