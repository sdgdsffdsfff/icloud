package com.icloud.insurance.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

public class InsuranceDomainEntityMethodFilter implements CallbackFilter {

	// callback index for save method
	private static final int GET = 0;
	// callback index for load method
	private static final int OTHER = 1;

	/**
	 * Specify which callback to use for the method being invoked.
	 * 
	 * @method the method being invoked.
	 * @return the callback index in the callback array for this method
	 */
	public int accept(Method method) {
		String name = method.getName();
		if (name.startsWith("get")) {
			return GET;
		}
		return OTHER;
	}

}
