package com.esolutions.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@Service
public class MongoDBConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public MongoClient mongoClient(@Value("${spring.mongodb.uri}") String connectionString) {
		ConnectionString connString = new ConnectionString(connectionString);
		MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder().applyConnectionString(connString)
				.writeConcern(WriteConcern.MAJORITY.withWTimeout(2500, TimeUnit.MILLISECONDS)).build());

		return mongoClient;
	}
}
