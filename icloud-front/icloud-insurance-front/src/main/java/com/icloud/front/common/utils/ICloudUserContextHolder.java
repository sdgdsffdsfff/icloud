package com.icloud.front.common.utils;

import com.icloud.front.user.pojo.UserInfo;

public class ICloudUserContextHolder {

	private static ThreadLocal<UserInfo> holder = new ThreadLocal<UserInfo>();

	public static UserInfo get() {
		return holder.get();
	}

	public static void set(UserInfo ri) {
		holder.set(ri);
	}

	public static void remove() {
		holder.remove();
	}

}
