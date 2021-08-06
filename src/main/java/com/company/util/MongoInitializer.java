package com.company.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * MongoInitializer.mongoClient Class initializes a mongoClient and returns it.
 * this method is used to initialize mongodb connection
 */
public class MongoInitializer {

    public MongoClient mongoClientInitializer() {
        final String uri = "mongodb://localhost:27017";
        return MongoClients.create(uri);
    }

}
