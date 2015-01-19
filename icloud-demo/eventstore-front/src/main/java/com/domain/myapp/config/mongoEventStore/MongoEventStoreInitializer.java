package com.domain.myapp.config.mongoEventStore;

import javax.annotation.Resource;

import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.MongoDBJournal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Configuration
public class MongoEventStoreInitializer {

	private static Logger log = LoggerFactory
			.getLogger(MongoEventStoreInitializer.class);

	@Resource(name = "mongoDBJournal")
	private MongoDBJournal mongoDBJournal;

	@Resource(name = "actorSystem")
	private ActorSystem actorSystem;

	@Bean(name = "mongoEventStore")
	public ActorRef initializeEventStore() {
		ActorRef eventStore = actorSystem.actorOf(
				EventStore.mkProps(mongoDBJournal), "mongoEventStore");
		return eventStore;
	}

}
