package com.mehmetyurekli.Mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository<T> implements Repository<T> {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<T> collection;

    public MongoRepository(String database, String collection, Class<T> tClass){
        this.mongoClient = MongoConnector.getInstance().getMongo();
        this.database = mongoClient.getDatabase(database);
        this.collection = this.database.getCollection(collection, tClass);
    }
    @Override
    public T getSingle(String field, String value) {
        return collection.find(eq(field, value)).first();
    }

    @Override
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        collection.find().forEach(t -> result.add(t));
        return result;
    }

    @Override
    public void insertOne(T pojo) {
        collection.insertOne(pojo);
    }

    @Override
    public void replace(String fieldToFind, String valueToFind, String field, String value) {
        collection.updateOne(eq(fieldToFind, valueToFind), eq(field, value));
    }

    @Override
    public void delete(String field, String value) {
        collection.deleteOne(eq(field, value));
    }
}
