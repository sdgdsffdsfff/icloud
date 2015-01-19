package com.domain.myapp.model;

import no.ks.eventstore2.Event;

public class CustomerEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aggregateRootId;
	private String aggregateType = AggregateType.Customer_AGGREGATE;

	private Object oldValue;
	private Object NewValue;
	private String changeKey;

	public CustomerEvent(String aggregateRootId) {
		this.aggregateRootId = aggregateRootId;
	}

	@Override
	public String getAggregateRootId() {
		return aggregateRootId;
	}

	@Override
	public String getAggregateType() {
		return aggregateType;
	}

	@Override
	public String getLogMessage() {
		return "This is a CustomerEvent";
	}

	public static CustomerEvent generateCustomerEvent(String id,
			String changeKey, Object oldValue, Object newValue) {
		CustomerEvent event = new CustomerEvent(id);
		event.setChangeKey(changeKey);
		event.setOldValue(oldValue);
		event.setNewValue(newValue);
		return event;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}

	public Object getNewValue() {
		return NewValue;
	}

	public void setNewValue(Object newValue) {
		NewValue = newValue;
	}

	public String getChangeKey() {
		return changeKey;
	}

	public void setChangeKey(String changeKey) {
		this.changeKey = changeKey;
	}

	public void setAggregateRootId(String aggregateRootId) {
		this.aggregateRootId = aggregateRootId;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	@Override
	public String toString() {
		return "CustomerEvent [aggregateRootId=" + aggregateRootId
				+ ", aggregateType=" + aggregateType + ", oldValue=" + oldValue
				+ ", NewValue=" + NewValue + ", changeKey=" + changeKey
				+ ", getCreated()=" + getCreated() + ", getJournalid()="
				+ getJournalid() + "]";
	}

}