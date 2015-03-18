package com.icloud.insurance.domain;

import com.icloud.framework.domain.AggregateRoot;
import com.icloud.framework.domain.BaseDomainEntity;

public abstract class InsurnaceBaseDomainEntity<T> extends BaseDomainEntity {
	protected T baseService;

	public InsurnaceBaseDomainEntity(AggregateRoot root, T t,
			boolean lazyLoading) {
		super(root, lazyLoading);
		this.baseService = t;
	}

	public InsurnaceBaseDomainEntity(AggregateRoot root, T t) {
		this(root, t, true);
	}

}
