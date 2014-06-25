package com.icloud.user.dict;

public class UserConstants {
	public enum COMMING {
		COM_COMMING("本站");

		private String name;

		private COMMING(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
