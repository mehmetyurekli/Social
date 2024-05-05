package com.mehmetyurekli.Mongo;

import java.util.List;

public interface Repository<T> {

    public T getSingle(String field, String value);
    public List<T> getAll();
    public void insertOne(T pojo);
    public void replace(String fieldToFind, String valueToFind, String field, String value);
    public void delete(String field, String value);

}
