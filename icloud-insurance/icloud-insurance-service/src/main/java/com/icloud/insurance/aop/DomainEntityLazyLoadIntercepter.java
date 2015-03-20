package com.icloud.insurance.aop;

public class DomainEntityLazyLoadIntercepter extends ICloudIntercepter {
	@Override
	public void beforeMethod() {
		System.out.println("log start...");
	}

	@Override
	protected void afterMethod() {
		System.out.println("log end...");
	}
}
