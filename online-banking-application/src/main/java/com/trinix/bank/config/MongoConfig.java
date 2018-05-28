package com.trinix.bank.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig {

	@Autowired
	private MongoClient mongoClient;

	@Bean
	public MongoClient mongoClient() {
		MongoClientOptions.Builder clientOptions = new MongoClientOptions.Builder();
		clientOptions.minConnectionsPerHost(1);
		clientOptions.connectionsPerHost(1);
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/", clientOptions));
		return mongoClient;
	}

	@Bean
	DisposableBean closeDatabaseConnections() {
		return new DisposableBean() {
			@Override
			public void destroy() throws Exception {
				mongoClient.close();
			}
		};
	}

}
