package com.domain.myapp.service;

import java.util.ArrayList;
import java.util.List;

import no.ks.eventstore2.Event;
import no.ks.eventstore2.eventstore.EventBatch;
import no.ks.eventstore2.eventstore.MongoDBJournal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.myapp.model.AggregateType;
import com.domain.myapp.model.CustomerID;

@Service("customerService")
public class CustomerService {

	@Autowired
	private MongoDBJournal mongodbJournal;

	public boolean addIDs() {
		CustomerID id = new CustomerID();
		mongodbJournal.saveEvent(id);
		return true;
	}

	public List<CustomerID> getAllIDs() {
		CustomerID id = new CustomerID();
		mongodbJournal.saveEvent(id);
		EventBatch eventBatch = mongodbJournal.loadEventsForAggregateId(
				AggregateType.Customer_ID, "2", "0");
		List<Event> events = eventBatch.getEvents();
		List<CustomerID> list = new ArrayList<CustomerID>();
		for (Event e : events) {
			list.add((CustomerID) e);
		}
		return list;
	}
}
