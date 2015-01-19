package com.domain.myapp.config.mongoEventStore;

import no.ks.eventstore2.DeadLetterLogger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.domain.myapp.config.eventstore.ProjectionErrorListener;

@Configuration
public class ActorSystemInitializer {

	private ActorSystem actorSystem;

	public ActorSystemInitializer() {
		initializeActorSystem();
	}

	private void initializeActorSystem() {
		actorSystem = ActorSystem.create("example");
		actorSystem.actorOf(Props.create(DeadLetterLogger.class));
	}

	@Bean(name = "actorSystem")
	public ActorSystem getActorSystem() {
		return actorSystem;
	}

	@Bean(name = "projectionErrorListener")
	public ActorRef getProjectionErrorListener() {
		return actorSystem.actorOf(Props.create(ProjectionErrorListener.class),
				"projectionErrorListener");
	}
}
