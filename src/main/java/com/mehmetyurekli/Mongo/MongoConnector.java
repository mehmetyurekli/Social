package com.mehmetyurekli.Mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnector {

    private static MongoConnector instance;
    private final MongoClient client;
    //private final String uri = "mongodb://localhost:27017";
    private String uri = "mongodb+srv://mehmetyurekli:10012003@cluster0.kk2hlzc.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    private MongoConnector() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(uri)).codecRegistry(codecRegistry).build();

        client = MongoClients.create(clientSettings);
    }

    public static MongoConnector getInstance() {
        if (instance == null) {
            instance = new MongoConnector();
        }
        return instance;
    }

    public MongoClient getMongo() {
        return client;
    }

}
