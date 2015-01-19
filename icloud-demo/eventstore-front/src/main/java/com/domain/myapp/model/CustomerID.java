package com.domain.myapp.model;

import no.ks.eventstore2.Event;

import org.joda.time.DateTime;

public class CustomerID extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2300133675222125156L;
	private String aggregateType = AggregateType.Customer_ID;
	private String aggreagetRootId;

	public CustomerID() {
		this.aggreagetRootId = "2";
		this.setCreated(new DateTime());
	}

	public String getAggreagetRootId() {
		return aggreagetRootId;
	}

	public void setAggreagetRootId(String aggreagetRootId) {
		this.aggreagetRootId = aggreagetRootId;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	@Override
	public String getAggregateType() {
		// TODO Auto-generated method stub
		return aggregateType;
	}

	@Override
	public String getLogMessage() {
		return "This is a CustomerID";
	}

	@Override
	public String getAggregateRootId() {
		// TODO Auto-generated method stub
		return aggreagetRootId;
	}

	@Override
	public String toString() {
		return "CustomerID [aggregateType=" + aggregateType
				+ ", aggreagetRootId=" + aggreagetRootId + ", getCreated()="
				+ getCreated() + ", getJournalid()=" + getJournalid() + "]";
	}

}
