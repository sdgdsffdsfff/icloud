package com.icloud.framework.domain;

public class AggregateRoot {
	protected int aggregateId;

	public AggregateRoot(int aggregateId) {
		this.aggregateId = aggregateId;
	}

	public int getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(int aggregateId) {
		this.aggregateId = aggregateId;
	}

}
