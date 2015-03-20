package com.icloud.insurance.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class ICloudIntercepter implements InvocationHandler {
	private Object target;

	public void setTarget(Object target) {
		this.target = target;
	}

	protected abstract void beforeMethod();

	protected abstract void afterMethod();

	@Override
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		beforeMethod();
		Object result = m.invoke(target, args);
		afterMethod();
		return result;
	}
}
