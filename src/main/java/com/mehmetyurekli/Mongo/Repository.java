package com.mehmetyurekli.Mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public interface Repository<T> {

    public T getSingle(String field, Object value);
    public List<T> getAll();
    public void insertOne(T pojo);
    public void replace(String fieldToFind, Object valueToFind, String field, Object value);
    public void pushToArray(String fieldToFind, Object valueToFind, String fieldToPush,Object push);
    public void pullFromArray(String fieldToFind, Object valueToFind, String fieldToPull,Object pull);
    public void delete(String fieldToFind, Object value);
}
