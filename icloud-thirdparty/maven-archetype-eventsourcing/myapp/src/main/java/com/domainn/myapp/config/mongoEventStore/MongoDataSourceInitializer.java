package com.domainn.myapp.config.mongoEventStore;

import java.net.UnknownHostException;
import java.util.Arrays;

import no.ks.eventstore2.eventstore.KryoClassRegistration;
import no.ks.eventstore2.eventstore.MongoDBJournal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.domain.myapp.model.CustomerAggregate;
import com.esotericsoftware.kryo.Kryo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
public class MongoDataSourceInitializer {
	private MongoClient mongoClient = null;
	private KryoClassRegistration kryoClassRegistration = new KryoClassRegistration() {
		@Override
		public void registerClasses(Kryo kryo) {
			kryo.register(CustomerAggregate.class, 1001);
		}
	};

	public MongoDataSourceInitializer() {
		try {
			mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Bean(name = "mongoClient")
	public MongoClient getMongoClient() {
		return this.mongoClient;
	}

	@Bean(name = "mongoDBJournal")
	public MongoDBJournal getMongoDBJournal() {
		DB journal = mongoClient.getDB("Journal");
		MongoDBJournal mongodbJournal = new MongoDBJournal(journal,
				kryoClassRegistration, Arrays.asList(new String[] { "Customer",
						"CustomerIDs" }), 10);
		mongodbJournal.open();
		return mongodbJournal;
	}

}
