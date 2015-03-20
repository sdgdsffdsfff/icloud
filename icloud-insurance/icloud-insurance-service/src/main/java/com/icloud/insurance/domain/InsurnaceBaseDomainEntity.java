package com.icloud.insurance.domain;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.framework.domain.BaseDomainEntity;
import com.icloud.insurance.cglib.InsuranceDomainEntityMethodFilter;
import com.icloud.insurance.cglib.InsuranceDomainEntityMethodInterceptor;

public abstract class InsurnaceBaseDomainEntity extends BaseDomainEntity {
	// protected T baseService;

	public static <T extends InsurnaceBaseDomainEntity> T getInsurance(
			Class clz, AggregateRoot root, Class objClz, Object t,
			boolean lazyLoading) {
		Class[] args = { AggregateRoot.class, objClz, boolean.class };
		Object[] argsValue = { root, t, lazyLoading };
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clz);

		InsuranceDomainEntityMethodFilter callbackFilter = new InsuranceDomainEntityMethodFilter();
		enhancer.setCallbackFilter(callbackFilter);
		InsuranceDomainEntityMethodInterceptor insuranceDomainEntityMethodInterceptor = new InsuranceDomainEntityMethodInterceptor();
		Callback[] callbacks = new Callback[] {
				insuranceDomainEntityMethodInterceptor, NoOp.INSTANCE };
		enhancer.setCallbacks(callbacks);
		T domainEntity = (T) enhancer.create(args, argsValue);
		insuranceDomainEntityMethodInterceptor
				.setInsurnaceBaseDomainEntity(domainEntity);
		if (!lazyLoading) {
			domainEntity.loading();
		}
		return domainEntity;
	}

	public InsurnaceBaseDomainEntity(AggregateRoot root, boolean lazyLoading) {
		super(root, lazyLoading);
		// this.baseService = t;
	}

	public InsurnaceBaseDomainEntity(AggregateRoot root) {
		this(root, true);
	}

}
