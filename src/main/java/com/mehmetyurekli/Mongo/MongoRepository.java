package com.mehmetyurekli.Mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.pull;
import static com.mongodb.client.model.Updates.push;

public class MongoRepository<T> implements Repository<T> {

    private final MongoCollection<T> collection;


    public MongoRepository(String database, String collection, Class<T> tClass) {
        MongoClient mongoClient = MongoConnector.getInstance().getMongo();
        MongoDatabase database1 = mongoClient.getDatabase(database);
        this.collection = database1.getCollection(collection, tClass);
    }


    @Override
    public T getSingle(String field, Object value) {
        return collection.find(eq(field, value)).first();
    }

    @Override
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        collection.find().forEach(t -> result.add(t));
        return result;
    }

    @Override
    public List<T> getAll(String field, Object value) {
        List<T> result = new ArrayList<>();
        collection.find(eq(field, value)).forEach(t -> result.add(t));
        return result;
    }

    @Override
    public void insertOne(T pojo) {
        collection.insertOne(pojo);
    }

    @Override
    public void replace(String fieldToFind, Object valueToFind, String field, Object value) {
        collection.updateOne(eq(fieldToFind, valueToFind), Updates.set(field, value));
    }

    @Override
    public void pushToArray(String fieldToFind, Object valueToFind, String fieldToPush, Object push) {
        collection.updateOne(eq(fieldToFind, valueToFind), push(fieldToPush, push));
    }

    @Override
    public void pullFromArray(String fieldToFind, Object valueToFind, String fieldToPull, Object pull) {
        collection.updateOne(eq(fieldToFind, valueToFind), pull(fieldToPull, pull));
    }

    @Override
    public void delete(String fieldToFind, Object value) {
        collection.deleteOne(eq(fieldToFind, value));
    }

}
