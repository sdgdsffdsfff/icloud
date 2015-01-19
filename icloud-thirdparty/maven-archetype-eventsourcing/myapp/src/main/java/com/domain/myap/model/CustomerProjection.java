package com.domain.myap.model;

import java.util.ArrayList;
import java.util.List;

import no.ks.eventstore2.Event;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.ask.Asker;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;

@Subscriber(AggregateType.Customer_AGGREGATE)
public class CustomerProjection extends Projection {

	private static Logger log = LoggerFactory
			.getLogger(CustomerProjection.class);

	private CustomerAggregate customer = new CustomerAggregate();

	private List<Event> events = new ArrayList<Event>();

	public static Props mkProps(ActorRef eventStore, String aggreagetRootId) {
		return Props.create(CustomerProjection.class, eventStore,
				aggreagetRootId);
	}

	public CustomerProjection(ActorRef eventStore, String aggreagetRootId) {
		super(eventStore);
		customer.setAggreagetRootId(aggreagetRootId);
	}

	@Handler
	public void handleEvent(Event event) {
		if (event.getAggregateRootId().equalsIgnoreCase(
				customer.getAggreagetRootId())) {
			// System.out.println(" a :" + event.getAggregateRootId());
			events.add(event);
			mutate(event);
		}
	}

	public void changeName(String newName) {
		CustomerEvent event = CustomerEvent.generateCustomerEvent(
				customer.getAggreagetRootId(), CustomerAggregate.NAME,
				customer.getCustomerName(), newName);
		apply(event);
	}

	public void mutate(Event event) {
		if (event instanceof CustomerEvent) {
			mutateCustomerEvent(event);
		}
	}

	public void mutateCustomerEvent(Event event) {
		CustomerEvent customerEvent = (CustomerEvent) event;
		customer.setCustomerName((String) customerEvent.getNewValue());
	}

	public void apply(Event event) {
		this.eventStore.tell(event, this.getSelf());
		handleEvent(event);
	}

	public List<Event> getEvents() {
		return events;
	}

	public CustomerAggregate getCustomerAggregate() {
		return this.customer;
	}

	// public static List<CustomerEvent> askEvents(ActorRef projection,
	// String aggregateType, String aggregateRootId) {
	// try {
	// return Asker.askProjection(projection, "getEvents", aggregateType,
	// aggregateRootId).list(CustomerEvent.class);
	// } catch (Exception e) {
	// throw new RuntimeException("Error when asking projection " + e);
	// }
	// }

	public static CustomerAggregate askCustomerAggregate(ActorRef projection) {
		try {
			return Asker.askProjection(projection, "getCustomerAggregate")
					.single(CustomerAggregate.class);
		} catch (Exception e) {
			throw new RuntimeException("Error when asking projection " + e);
		}
	}

}