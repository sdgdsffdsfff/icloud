package com.domain.myapp.client;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import no.ks.eventstore2.DeadLetterLogger;
import no.ks.eventstore2.Event;
import no.ks.eventstore2.ask.Asker;
import no.ks.eventstore2.eventstore.EventBatch;
import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.KryoClassRegistration;
import no.ks.eventstore2.eventstore.MongoDBJournal;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.domain.myapp.config.eventstore.ActorSystemInitializer;
import com.domain.myapp.model.AggregateType;
import com.domain.myapp.model.CustomerAggregate;
import com.domain.myapp.model.CustomerID;
import com.domain.myapp.model.CustomerProjection;
import com.esotericsoftware.kryo.Kryo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoClient2 {
	// static ActorSystem _system = ActorSystem.create("TestSys", ConfigFactory
	// .load().getConfig("TestSys"));
	static ActorSystem _system = new ActorSystemInitializer().getActorSystem();
	MongoDBJournal mongodbJournal;
	// MongoDBJournal monggodbArregateJournal;
	MongoClient mongoClient;
	private KryoClassRegistration kryoClassRegistration = new KryoClassRegistration() {
		@Override
		public void registerClasses(Kryo kryo) {
			kryo.register(CustomerAggregate.class, 1001);
		}
	};

	public void setUp() throws UnknownHostException {
		mongoClient = new MongoClient(new ServerAddress("localhost", 27017));

		DB journal = mongoClient.getDB("Journal");
		mongodbJournal = new MongoDBJournal(journal, kryoClassRegistration,
				Arrays.asList(new String[] { "Customer", "CustomerIDs" }), 10);
		mongodbJournal.open();

	}

	public void addIDs() {
		CustomerID id = new CustomerID();
		mongodbJournal.saveEvent(id);
		EventBatch eventBatch = mongodbJournal.loadEventsForAggregateId(
				AggregateType.Customer_ID, "2", "0");
		List<Event> events = eventBatch.getEvents();
		for (Event e : events) {
			System.out.println(e);
		}
	}

	public void testSendingIncompleteEvent() throws Exception {
		// mongodbJournal.saveEvent(new AggEvent("id2", "agg"));
		System.out.println("-----------------");
		ActorSystem actorSystem = ActorSystem.create("example");
		actorSystem.actorOf(Props.create(DeadLetterLogger.class));
		ActorRef eventStore = actorSystem.actorOf(
				EventStore.mkProps(mongodbJournal), "eventStore");
		System.out.println("..........................");
		ActorRef customerAggregate = actorSystem.actorOf(
				CustomerProjection.mkProps(eventStore, mongoClient, "3"),
				"Customer");
		System.out.println("///////////////////////");
		// Boolean single = Asker.askProjection(customerAggregate, "changeName",
		// "nihao10").single(Boolean.class);

		// CustomerAggregate customer = CustomerProjection
		// .askCustomerAggregate(customerAggregate);
		System.out.println("------------------");
		Boolean single = Asker.askProjection(customerAggregate, "changeName",
				"nihao3").single(Boolean.class);
		// CustomerProjection.takeSnapshot(customerAggregate);
		// System.out.println(customer);
		// Future<Object> formStatus = ask(customerAggregate,
		// call("changeName", "nihao8"), 3000);
		// customer =
		// CustomerProjection.askCustomerAggregate(customerAggregate);
		// System.out.println(customer);
	}

	public static void main(String[] args) throws Exception {
		MongoClient2 client = new MongoClient2();
		client.setUp();
		// client.testSendingIncompleteEvent();
		client.addIDs();
	}
}
