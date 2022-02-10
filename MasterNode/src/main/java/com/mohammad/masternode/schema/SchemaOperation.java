package com.mohammad.masternode.schema;

public interface SchemaOperation<T> {
    T get(String key);
    void add(String key);
}
