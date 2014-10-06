package com.icloud.front.juhuasuan.pojo;

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
		SINGLE("0", "单品"), SITE("1", "聚划算");
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
}
