package com.mohammad.replicanode.utils;

import com.mohammad.replicanode.cache.LRUCache;
import com.mohammad.replicanode.schema.Database;

import java.util.List;

public class CacheUtils {

    private static final int MAX_CAPACITY = 10000;
    private static final LRUCache<String, Object> GLOBAL_CACHE = new LRUCache<>(MAX_CAPACITY);


    private CacheUtils(){
        throw new AssertionError();
    }
    public static void putIntoGlobalCache(List<Database> databaseGroup){
        databaseGroup.forEach(data -> GLOBAL_CACHE.put(data.getDatabaseName(),data));

        databaseGroup.forEach(data -> data.getCollectionGroup()
                .forEach(GLOBAL_CACHE::put));

        databaseGroup.forEach(data -> data.getCollectionGroup()
                .forEach((key,value)-> value.getDocumentGroup().forEach(GLOBAL_CACHE::put)));
    }

    public static void clearCache(){
       GLOBAL_CACHE.clearAll();
    }

    public static Object getCacheObject(String key){
        return GLOBAL_CACHE.get(key);
    }
}
