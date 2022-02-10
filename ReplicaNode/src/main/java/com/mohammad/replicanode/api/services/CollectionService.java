package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Collection;
import org.springframework.stereotype.Service;

import static com.mohammad.replicanode.api.services.DatabaseService.getDatabase;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;

@Service
public class CollectionService {

    public Collection getCollection(String databaseName, String collectionName) {
        if (getCacheObject(collectionName) == null)
            return getDatabase(databaseName).get(collectionName);
        return (Collection) getCacheObject(collectionName);
    }
}
