package com.icloud.insurance.db;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.domain.valueobject.InsuranceEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.InsuranceCategoryEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.InsuranceInfoEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemMenuEnum;
import com.icloud.insurance.domain.valueobject.InsuranceEnum.SystemStatusEnum;
import com.icloud.insurance.model.InsuranceAttribute;
import com.icloud.insurance.service.InsuranceAttributeService;

public class InitDB {
	public static void init(InsuranceAttributeService insuranceAttributeService) {
		initSystemMenu(insuranceAttributeService);
		initInsuranceCategoryEnum(insuranceAttributeService);
		initInsuranceInfoEnum(insuranceAttributeService);
	}

	private static void initInsuranceInfoEnum(
			InsuranceAttributeService insuranceAttributeService) {
		InsuranceInfoEnum[] values = InsuranceInfoEnum.values();
		for (InsuranceInfoEnum insuranceInfoEnum : values) {
			if (insuranceAttributeService.existsUUID(insuranceInfoEnum
					.getUuid())) {
				InsuranceAttribute fatherInsuranceAttribute = insuranceAttributeService
						.getByUUID(insuranceInfoEnum.getFather_uuid());
				InsuranceAttribute InsuranceAttribute = convertInsuranceAttribute(
						insuranceInfoEnum, fatherInsuranceAttribute);
				if (ICloudUtils.isNotNull(InsuranceAttribute)) {
					insuranceAttributeService.save(InsuranceAttribute);
				}
			}
		}

	}

	private static com.icloud.insurance.model.InsuranceAttribute convertInsuranceAttribute(
			InsuranceInfoEnum insuranceInfoEnum,
			InsuranceAttribute fatherInsuranceAttribute) {
		if (!ICloudUtils.isNotNull(insuranceInfoEnum)
				|| !ICloudUtils.isNotNull(fatherInsuranceAttribute))
			return null;
		InsuranceAttribute insuranceAttribute = new InsuranceAttribute();
		insuranceAttribute.setAttributeName(insuranceInfoEnum.getName());
		insuranceAttribute.setFatherId(fatherInsuranceAttribute.getId());
		insuranceAttribute.setFatherName(fatherInsuranceAttribute
				.getAttributeName());
		insuranceAttribute.setStatus(SystemStatusEnum.OK.getStatus());
		insuranceAttribute.setLevel(fatherInsuranceAttribute.getLevel() + 1);
		insuranceAttribute.setUuid(insuranceInfoEnum.getUuid());
		return insuranceAttribute;
	}

	private static void initInsuranceCategoryEnum(
			InsuranceAttributeService insuranceAttributeService) {
		InsuranceCategoryEnum[] values = InsuranceCategoryEnum.values();
		for (InsuranceCategoryEnum insuranceCategoryEnum : values) {
			if (insuranceAttributeService.existsUUID(insuranceCategoryEnum
					.getUuid())) {
				InsuranceAttribute fatherInsuranceAttribute = insuranceAttributeService
						.getByUUID(insuranceCategoryEnum.getFather_uuid());
				InsuranceAttribute InsuranceAttribute = convertInsuranceAttribute(
						insuranceCategoryEnum, fatherInsuranceAttribute);
				if (ICloudUtils.isNotNull(InsuranceAttribute)) {
					insuranceAttributeService.save(InsuranceAttribute);
				}
			}
		}
	}

	private static InsuranceAttribute convertInsuranceAttribute(
			InsuranceCategoryEnum insuranceCategoryEnum,
			InsuranceAttribute fatherInsuranceAttribute) {
		if (!ICloudUtils.isNotNull(insuranceCategoryEnum)
				|| !ICloudUtils.isNotNull(fatherInsuranceAttribute))
			return null;
		InsuranceAttribute InsuranceAttribute = new InsuranceAttribute();
		InsuranceAttribute.setAttributeName(insuranceCategoryEnum.getName());
		InsuranceAttribute.setFatherId(fatherInsuranceAttribute.getId());
		InsuranceAttribute.setFatherName(fatherInsuranceAttribute
				.getAttributeName());
		InsuranceAttribute.setStatus(SystemStatusEnum.OK.getStatus());
		InsuranceAttribute.setLevel(fatherInsuranceAttribute.getLevel() + 1);
		InsuranceAttribute.setUuid(insuranceCategoryEnum.getUuid());
		return InsuranceAttribute;
	}

	private static void initSystemMenu(
			InsuranceAttributeService insuranceAttributeService) {
		SystemMenuEnum[] values = InsuranceEnum.SystemMenuEnum.values();
		for (SystemMenuEnum systemMenuEnum : values) {
			if (insuranceAttributeService.existsUUID(systemMenuEnum.getUuid())) {
				InsuranceAttribute InsuranceAttribute = convertInsuranceAttribute(systemMenuEnum);
				if (ICloudUtils.isNotNull(InsuranceAttribute)) {
					insuranceAttributeService.save(InsuranceAttribute);
				}
			}
		}
	}

	public static InsuranceAttribute convertInsuranceAttribute(
			SystemMenuEnum systemMenuEnum) {
		if (!ICloudUtils.isNotNull(systemMenuEnum))
			return null;
		InsuranceAttribute InsuranceAttribute = new InsuranceAttribute();
		InsuranceAttribute.setAttributeName(systemMenuEnum.getName());
		InsuranceAttribute.setFatherId(0);
		InsuranceAttribute.setFatherName("");
		InsuranceAttribute.setStatus(SystemStatusEnum.OK.getStatus());
		InsuranceAttribute.setLevel(0);
		InsuranceAttribute.setUuid(systemMenuEnum.getUuid());
		return InsuranceAttribute;
	}

}
