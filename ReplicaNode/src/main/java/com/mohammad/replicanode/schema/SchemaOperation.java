package com.mohammad.replicanode.schema;

public interface SchemaOperation<T> {
    T get(String key);
    void add(String key);
}


