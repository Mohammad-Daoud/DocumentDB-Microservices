package com.mohammad.replicanode.utils;

import com.mohammad.replicanode.cache.LRUCache;
import com.mohammad.replicanode.schema.Database;

import java.util.List;

public class CacheUtils {
    private static final AppLogger LOGGER = AppLogger.create("CacheUtils logger: ");
    private static final int MAX_CAPACITY = 1000;
    private static  LRUCache<String, Object> globalCache = new LRUCache<>(MAX_CAPACITY);


    private CacheUtils(){
        throw new AssertionError();
    }
    public static void addALlIntoGlobalCache(List<Database> databaseGroup){
        databaseGroup.forEach(data -> globalCache.put(data.getDatabaseName(),data));

        databaseGroup.forEach(data -> data.getCollectionGroup()
                .forEach(globalCache::put));

        databaseGroup.forEach(data -> data.getCollectionGroup()
                .forEach((key,value)-> value.getDocumentGroup().forEach(globalCache::put)));
    }

    public static void add(String key, Object value){
        globalCache.put(key, value);
    }


    public static void clearCache(){
        globalCache = new LRUCache<>(MAX_CAPACITY);
        LOGGER.log("cache cleaned successfully");
    }

    public static Object getCacheObject(String key){
        return globalCache.get(key);
    }
}
