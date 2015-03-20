package com.icloud.insurance.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.icloud.insurance.domain.InsurnaceBaseDomainEntity;

public class InsuranceDomainEntityMethodInterceptor implements
		MethodInterceptor {
	private InsurnaceBaseDomainEntity insurnaceBaseDomainEntity;

	public InsurnaceBaseDomainEntity getInsurnaceBaseDomainEntity() {
		return insurnaceBaseDomainEntity;
	}

	public void setInsurnaceBaseDomainEntity(
			InsurnaceBaseDomainEntity insurnaceBaseDomainEntity) {
		this.insurnaceBaseDomainEntity = insurnaceBaseDomainEntity;
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result = null;
		insurnaceBaseDomainEntity.checkLoad();
		result = proxy.invokeSuper(obj, args);
		return result;
	}
}
