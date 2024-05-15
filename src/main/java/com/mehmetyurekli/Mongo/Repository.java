package com.mehmetyurekli.Mongo;

import java.util.List;

public interface Repository<T> {

    T getSingle(String field, Object value);

    List<T> getAll();

    List<T> getAll(String field, Object value);

    void insertOne(T pojo);

    void replace(String fieldToFind, Object valueToFind, String field, Object value);

    void pushToArray(String fieldToFind, Object valueToFind, String fieldToPush, Object push);

    void pullFromArray(String fieldToFind, Object valueToFind, String fieldToPull, Object pull);

    void delete(String fieldToFind, Object value);
}
