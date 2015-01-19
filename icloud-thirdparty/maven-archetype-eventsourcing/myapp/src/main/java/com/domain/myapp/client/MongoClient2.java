package com.domain.myapp.client;

import static akka.pattern.Patterns.ask;
import static akka.testkit.JavaTestKit.duration;
import static no.ks.eventstore2.projection.CallProjection.call;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import no.ks.eventstore2.DeadLetterLogger;
import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.KryoClassRegistration;
import no.ks.eventstore2.eventstore.MongoDBJournal;
import scala.concurrent.Await;
import scala.concurrent.Future;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.domain.myap.model.CustomerAggregate;
import com.domain.myap.model.CustomerEvent;
import com.domain.myap.model.CustomerProjection;
import com.domain.myapp.config.eventstore.ActorSystemInitializer;
import com.esotericsoftware.kryo.Kryo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoClient2 {
	// static ActorSystem _system = ActorSystem.create("TestSys", ConfigFactory
	// .load().getConfig("TestSys"));
	static ActorSystem _system = new ActorSystemInitializer().getActorSystem();
	MongoDBJournal mongodbJournal;
	private KryoClassRegistration kryoClassRegistration = new KryoClassRegistration() {
		@Override
		public void registerClasses(Kryo kryo) {
			kryo.register(CustomerAggregate.class, 1001);
		}
	};

	public void setUp() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new ServerAddress(
				"localhost", 27017));

		DB journal = mongoClient.getDB("Journal");
		mongodbJournal = new MongoDBJournal(journal, kryoClassRegistration,
				Arrays.asList(new String[] { "Customer" }), 10);
		mongodbJournal.open();
	}

	public void testSendingIncompleteEvent() throws Exception {
		// mongodbJournal.saveEvent(new AggEvent("id2", "agg"));
		System.out.println("-----------------");
		ActorSystem actorSystem = ActorSystem.create("example");
		actorSystem.actorOf(Props.create(DeadLetterLogger.class));
		ActorRef eventStore = actorSystem.actorOf(
				EventStore.mkProps(mongodbJournal), "eventStore");
		ActorRef customerAggregate = actorSystem.actorOf(
				CustomerProjection.mkProps(eventStore, "3"), "Customer");
		CustomerAggregate customer = CustomerProjection
				.askCustomerAggregate(customerAggregate);
		System.out.println(customer);
		Future<Object> formStatus = ask(customerAggregate,
				call("changeName", "nihao7"), 3000);
		customer = CustomerProjection.askCustomerAggregate(customerAggregate);
		System.out.println(customer);
	}

	public static void main(String[] args) throws Exception {
		MongoClient2 client = new MongoClient2();
		client.setUp();
		client.testSendingIncompleteEvent();
	}
}
