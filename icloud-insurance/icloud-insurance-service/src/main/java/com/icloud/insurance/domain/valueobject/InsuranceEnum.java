package com.icloud.insurance.domain.valueobject;

public class InsuranceEnum {
	/**
	 * 0****
	 * 
	 * @author cuijiangning609
	 *
	 */
	public enum SystemMenuEnum {
		INSURANCECATEGORY("100000", "保险类别"), INSURANCEINFO("200000", "保险信息"), INSURANCECOMPANY(
				"300000", "保险公司");
		private String uuid;
		private String name;

		private SystemMenuEnum(String uuid, String name) {
			this.uuid = uuid;
			this.name = name;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	/**
	 * 保险信息属性
	 */
	public enum InsuranceInfoEnum {
		SAFEGUARDAGE("200002", "保障年龄"), SAFEGUARDTIME("200003", "保障期限"), SUITEPEOPLE(
				"200004", "适合人群"), PRODUCTHIGHLIGHTS("200005", "产品亮点"), PRODUCTFEATURES(
				"200006", "产品特色"), PRODUCTTIPS("200007", "产品提示"), RECOMMEND_TIPS(
				"200008", "特别推荐");
		public String uuid = null;
		public String name = null;
		public String father_uuid = null;

		private InsuranceInfoEnum(String uuid, String name) {
			this.uuid = uuid;
			this.name = name;
			this.father_uuid = SystemMenuEnum.INSURANCEINFO.getUuid();
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFather_uuid() {
			return father_uuid;
		}

		public void setFather_uuid(String father_uuid) {
			this.father_uuid = father_uuid;
		}
	}

	/**
	 * 1****
	 * 
	 * @author cuijiangning609
	 *
	 */
	public enum InsuranceCategoryEnum {
		ACCIDENT_INSURANCE("100001", "意外保险");
		private String uuid;
		private String name;
		private String father_uuid;

		private InsuranceCategoryEnum(String uuid, String name) {
			this.uuid = uuid;
			this.name = name;
			this.father_uuid = SystemMenuEnum.INSURANCECATEGORY.getUuid();
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFather_uuid() {
			return father_uuid;
		}

		public void setFather_uuid(String father_uuid) {
			this.father_uuid = father_uuid;
		}

	}

	public enum SystemStatusEnum {
		OK(1), NO_EXIST(-1);

		private int status;

		private SystemStatusEnum(int status) {
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

}
