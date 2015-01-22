package com.domain.myapp.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import no.ks.eventstore2.Event;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.TakeSnapshot;
import no.ks.eventstore2.ask.Asker;
import no.ks.eventstore2.eventstore.CustomerTakeSnapshot;
import no.ks.eventstore2.projection.MongoDbProjection2;
import no.ks.eventstore2.projection.Subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Props;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mongodb.MongoClient;

@Subscriber(AggregateType.Customer_AGGREGATE)
public class CustomerProjection extends MongoDbProjection2 {
	private static Kryo kryo = new Kryo();
	private static Logger log = LoggerFactory
			.getLogger(CustomerProjection.class);

	private CustomerAggregate customer = new CustomerAggregate();
	private CustomerAggregate snapshot = new CustomerAggregate();
	private String version = "00";
	private MongoClient mongoClient;

	private List<Event> events = new ArrayList<Event>();

	public static Props mkProps(ActorRef eventStore, MongoClient mongoClient,
			String aggreagetRootId) {
		return Props.create(CustomerProjection.class, eventStore, mongoClient,
				aggreagetRootId);
	}

	public CustomerProjection(ActorRef eventStore, MongoClient mongoClient,
			String aggreagetRootId) {
		super(eventStore, mongoClient);
		this.mongoClient = mongoClient;
		customer.setAggreagetRootId(aggreagetRootId);
		// this.preStart();
	}

	@Override
	protected byte[] serializeData() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Output output = new Output(outputStream);
		kryo.writeClassAndObject(output, customer);
		output.close();
		return outputStream.toByteArray();
	}

	@Override
	protected void deSerializeData(byte[] bytes) {
		Input input = new Input(bytes);
		customer = (CustomerAggregate) kryo.readClassAndObject(input);
		input = new Input(bytes);
		snapshot = (CustomerAggregate) kryo.readClassAndObject(input);
	}

	@Override
	protected String getSnapshotDataVersion() {
		return "2";
	}

	@Handler
	public void handleEvent(Event event) {
		if (event.getAggregateRootId().equalsIgnoreCase(
				customer.getAggreagetRootId())) {
			version = event.getJournalid();
			events.add(event);
			mutate(event);
		}
	}

	public boolean changeName(String newName) {
		CustomerEvent event = CustomerEvent.generateCustomerEvent(
				customer.getAggreagetRootId(), CustomerAggregate.NAME,
				customer.getCustomerName(), newName);
		apply(event);
		return true;
	}

	public boolean changeYear(Integer newYear) {
		CustomerEvent event = CustomerEvent.generateCustomerEvent(
				customer.getAggreagetRootId(), CustomerAggregate.YEAR,
				customer.getCustomerYear(), newYear);
		apply(event);
		return true;
	}

	public void mutate(Event event) {
		if (event instanceof CustomerEvent) {
			mutateCustomerEvent(event);
		}
	}

	public void mutateCustomerEvent(Event event) {
		CustomerEvent customerEvent = (CustomerEvent) event;
		if (customerEvent.getChangeKey().equalsIgnoreCase(
				CustomerAggregate.NAME)) {
			customer.setCustomerName((String) customerEvent.getNewValue());
		} else if (customerEvent.getChangeKey().equalsIgnoreCase(
				CustomerAggregate.YEAR)) {
			customer.setCustomerYear((Integer) customerEvent.getNewValue());
		}
		customer.setVersion(event.getJournalid());
	}

	public void apply(Event event) {
		this.eventStore.tell(event, this.getSelf());
		handleEvent(event);
	}

	public List<Event> getEvents() {
		return events;
	}

	public CustomerAggregate getSnapShot() {
		return this.snapshot;
	}

	public CustomerAggregate getCustomerAggregate() {
		return this.customer;
	}

	public static CustomerAggregate askCustomerAggregate(ActorRef projection) {
		try {
			return Asker.askProjection(projection, "getCustomerAggregate")
					.single(CustomerAggregate.class);
		} catch (Exception e) {
			throw new RuntimeException("Error when asking projection " + e);
		}
	}

	public static boolean changeAttribute(ActorRef projection,
			String methodName, Object newValue) {
		Boolean result;
		try {
			result = Asker.askProjection(projection, methodName, newValue)
					.single(Boolean.class);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error when asking projection " + e);
		}
	}

	public static CustomerAggregate getSN(ActorRef projection) {
		try {
			return Asker.askProjection(projection, "getSnapShot").single(
					CustomerAggregate.class);
		} catch (Exception e) {
			throw new RuntimeException("Error when asking projection " + e);
		}
	}

	public static boolean changeCustomerName(ActorRef projection,
			String customerValue) {
		return changeAttribute(projection, "changeName", customerValue);
	}

	public static boolean changeCustomerYear(ActorRef projection, int newYear) {
		return changeAttribute(projection, "changeYear", newYear);
	}

	public static List<Event> askEvents(ActorRef projection) {
		try {
			return Asker.askProjection(projection, "getEvents").list(
					Event.class);
		} catch (Exception e) {
			throw new RuntimeException("Error when asking projection " + e);
		}
	}

	public static void takeSnapshot(ActorRef projection) {
		CustomerAggregate customer = CustomerProjection
				.askCustomerAggregate(projection);
		projection.tell(new TakeSnapshot(), null);
	}

	public static void takeSnapshot(ActorRef projection, int jid) {
		CustomerAggregate customer = CustomerProjection
				.askCustomerAggregate(projection);
		projection.tell(new CustomerTakeSnapshot(jid), null);
	}

	@Override
	protected String getAggregateRootId() {
		// TODO Auto-generated method stub
		return this.customer.getAggreagetRootId();
	}

}