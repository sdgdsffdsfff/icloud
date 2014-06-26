package com.icloud.front.common.utils;

public class ICloudUserContextHolder {

	private static ThreadLocal<Object> holder = new ThreadLocal<Object>();

	public static Object get() {
		return holder.get();
	}

	public static void set(Object ri) {
		holder.set(ri);
	}

	public static void remove() {
		holder.remove();
	}

}
