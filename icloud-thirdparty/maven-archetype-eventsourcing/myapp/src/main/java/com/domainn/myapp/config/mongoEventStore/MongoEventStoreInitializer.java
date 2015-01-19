package com.domainn.myapp.config.mongoEventStore;

import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.H2JournalStorage;
import no.ks.eventstore2.eventstore.JournalStorage;
import no.ks.eventstore2.eventstore.MongoDBJournal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.domain.myapp.config.eventstore.EventStoreClassRegistration;
import com.domain.myapp.monitoring.ApplicationHasStartedEvent;

@Configuration
public class MongoEventStoreInitializer {

	private static Logger log = LoggerFactory
			.getLogger(MongoEventStoreInitializer.class);

	@Autowired
	private MongoDBJournal mongoDBJournal;

	@Autowired
	private ActorSystem actorSystem;

	@Bean(name = "mongoEventStore")
	public ActorRef initializeEventStore() {
		ActorRef eventStore = actorSystem.actorOf(
				EventStore.mkProps(mongoDBJournal), "mongoEventStore");
		return eventStore;
	}

}
