package com.icloud.insurance.domain.valueobject;

public class InsuranceEnum {
	/**
	 * 0****
	 * 
	 * @author cuijiangning609
	 *
	 */
	public enum SystemMenuEnum {
		INSURANCECATEGORY("00001", "保险类别"), SAFEGUARDAGE("00002", "保障年龄"), SAFEGUARDTIME(
				"00003", "保障期限"), SUITEPEOPLE("00004", "适合人群"), PRODUCTHIGHLIGHTS(
				"00005", "产品亮点"), PRODUCTFEATURES("00006", "产品特色"), PRODUCTTIPS(
				"00007", "产品提示"), RECOMMEND_TIPS("00008", "特别推荐");
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
	 * 1****
	 * 
	 * @author cuijiangning609
	 *
	 */
	public enum InsuranceCategoryEnum {
		ACCIDENT_INSURANCE("10001", "意外保险");
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
