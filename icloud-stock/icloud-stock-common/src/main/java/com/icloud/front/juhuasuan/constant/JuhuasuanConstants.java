package com.icloud.front.juhuasuan.constant;

public class JuhuasuanConstants {
	public enum JUHUASUANSTATUS {
		RUNNING("0", "运行"), PAUSE("1", "暂停"), CANCEL("2", "作废");
		private String id;
		private String name;

		private JUHUASUANSTATUS(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public static JUHUASUANSTATUS value(String id) {
			JUHUASUANSTATUS[] values = JUHUASUANSTATUS.values();
			for (JUHUASUANSTATUS value : values) {
				if (value.getId().equalsIgnoreCase(id)) {
					return value;
				}
			}
			return RUNNING;
		}
	}

	public enum JUHUASUANTYPE {
		SINGLE("0", "店铺"), SITE("1", "聚划算");
		private String id;
		private String name;

		private JUHUASUANTYPE(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public static JUHUASUANTYPE value(String id) {
			JUHUASUANTYPE[] values = JUHUASUANTYPE.values();
			for (JUHUASUANTYPE value : values) {
				if (value.getId().equalsIgnoreCase(id)) {
					return value;
				}
			}
			return SINGLE;
		}
	}

	public enum JUHUASUANSOLIDIFY {
		NOTSOLIDIFY("0", "不加固"), SOLIDIFY("1", "加固");
		private String id;
		private String name;

		private JUHUASUANSOLIDIFY(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public static JUHUASUANSOLIDIFY value(String id) {
			JUHUASUANSOLIDIFY[] values = JUHUASUANSOLIDIFY.values();
			for (JUHUASUANSOLIDIFY value : values) {
				if (value.getId().equalsIgnoreCase(id)) {
					return value;
				}
			}
			return NOTSOLIDIFY;
		}
	}

	public enum JUHUASUANURLTYPE {
		COMMON_URL(0, "普通链接"), SUPER_URL(1, "超级链接");
		private int id;
		private String name;

		private JUHUASUANURLTYPE(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public static JUHUASUANURLTYPE value(int id) {
			JUHUASUANURLTYPE[] values = JUHUASUANURLTYPE.values();
			for (JUHUASUANURLTYPE value : values) {
				if (value.getId() == id) {
					return value;
				}
			}
			return COMMON_URL;
		}
	}
}
