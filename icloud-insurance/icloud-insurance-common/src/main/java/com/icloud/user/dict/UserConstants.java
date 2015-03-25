package com.icloud.user.dict;

public class UserConstants {

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

	public enum UserType {
		SUPER_USER(0, "超级用户"), POXY_USER(2, "代理中介"), NORMAL_USER(1, "普通用户");
		private int id;
		private String userType;

		private UserType(int id, String userType) {
			this.id = id;
			this.userType = userType;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public static UserType getById(int id) {
			for (UserType userType : UserType.values()) {
				if (id == userType.getId())
					return userType;
			}
			return NORMAL_USER;
		}

	}

}
