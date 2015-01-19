package com.domain.myapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import no.ks.eventstore2.Event;
import no.ks.eventstore2.eventstore.EventBatch;
import no.ks.eventstore2.eventstore.MongoDBJournal;

import org.springframework.stereotype.Service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.domain.myapp.model.AggregateType;
import com.domain.myapp.model.CustomerAggregate;
import com.domain.myapp.model.CustomerID;
import com.domain.myapp.model.CustomerProjection;
import com.domain.myapp.util.IdUtil;
import com.mongodb.MongoClient;

@Service("customerService")
public class CustomerService {

	@Resource(name = "mongoDBJournal")
	private MongoDBJournal mongoDBJournal;
	@Resource(name = "actorSystem")
	private ActorSystem actorSystem;

	@Resource(name = "mongoEventStore")
	private ActorRef mongoEventStore;

	@Resource(name = "mongoClient")
	private MongoClient mongoClient;

	// private CustomerService customerService;
	public boolean addIDs() {
		CustomerID id = new CustomerID();
		mongoDBJournal.saveEvent(id);
		return true;
	}

	public List<CustomerID> getAllIDs() {
		// CustomerID id = new CustomerID();
		EventBatch eventBatch = mongoDBJournal.loadEventsForAggregateId(
				AggregateType.Customer_ID, "2", "0");
		List<Event> events = eventBatch.getEvents();
		List<CustomerID> list = new ArrayList<CustomerID>();
		for (Event e : events) {
			CustomerID cid = (CustomerID) e;
			list.add(cid);
		}
		return list;
	}

	public CustomerAggregate getCustomer(String aid) {
		ActorRef customerAggregate = getCustomerProjection(aid);
		CustomerAggregate customer = CustomerProjection
				.askCustomerAggregate(customerAggregate);
		return customer;
	}

	public ActorRef getCustomerProjection(String aid) {
		ActorRef customerAggregate = actorSystem.actorOf(
				CustomerProjection.mkProps(mongoEventStore, mongoClient, aid),
				"Customer_" + IdUtil.createUUID());
		return customerAggregate;
	}

	public List<Event> getAllEventByAid(String aid) {
		ActorRef customerAggregate = getCustomerProjection(aid);
		return CustomerProjection.askEvents(customerAggregate);
	}

	public void changeCustomerName(String aid, String changeValue) {
		ActorRef customerAggregate = getCustomerProjection(aid);
		CustomerProjection.changeCustomerName(customerAggregate, changeValue);
	}

	public void changeCustomerYear(String aid, int year) {
		ActorRef customerAggregate = getCustomerProjection(aid);
		CustomerProjection.changeCustomerYear(customerAggregate, year);
	}
}
