package com.icloud.insurance.domain.model;

public abstract class BaseEntity {
	protected AggregateRoot aggregateRoot;
	protected boolean lazyLoading;

	public BaseEntity(AggregateRoot root, boolean lazyLoading) {
		this.aggregateRoot = root;
		this.lazyLoading = lazyLoading;
	}

	public abstract void loadingEntity();
}
