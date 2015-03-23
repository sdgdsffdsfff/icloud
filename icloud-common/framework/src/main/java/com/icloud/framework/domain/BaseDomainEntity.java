package com.icloud.framework.domain;

public abstract class BaseDomainEntity {
	protected AggregateRoot aggregateRoot;
	protected boolean isLoaded;
	private boolean lazyLoading;

	public BaseDomainEntity(AggregateRoot root, boolean lazyLoading) {
		this.aggregateRoot = root;
		this.isLoaded = false;
		this.lazyLoading = lazyLoading;
	}

	public void loading() {
		if (lazyLoading) {
		} else {
			loadEntity();
		}
	}

	public synchronized void loadEntity() {
		if (!isLoaded) {
			doLoadEntity();
			isLoaded = true;
		}
	}

	public void checkLoad() {
		if (!isLoaded) {
			loadEntity();
		}
	}

	public abstract void doLoadEntity();

	public abstract void saveOrUpdateEntity();

	public abstract void deleteEntity();
}
