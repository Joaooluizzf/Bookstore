package br.com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.port}")
	int mongoPort;

	@Value("${spring.data.mongodb.database}")
	String mongoDatabase;

	@Value("${spring.data.mongodb.host}")
	String mongoHost;

	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoHost, mongoPort);
	}

}