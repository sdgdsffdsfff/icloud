package com.icloud.user.dict;

public class UserConstants {
	public final static int SUPER_USER = 0;
	public final static int NORMAL_USER = 1;
	public final static int POXY_USER = 2;

	public final static int OPEN_USER_OPER = 1;
	public final static int CLOSE_USER_OPER = 0;
	
	public final static int USER_LEVEL_LIMIT = 3;

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
