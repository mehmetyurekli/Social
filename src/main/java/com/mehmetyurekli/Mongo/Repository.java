package com.mehmetyurekli.Mongo;

import java.util.List;

public interface Repository<T> {

    public T getSingle(String field, Object value);
    public List<T> getAll();
    public void insertOne(T pojo);
    public void replace(String fieldToFind, Object valueToFind, String field, Object value);
    public void delete(String field, String value);

}
