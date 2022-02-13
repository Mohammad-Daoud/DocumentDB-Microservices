package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Collection;
import com.mohammad.replicanode.utils.CacheUtils;
import org.springframework.stereotype.Service;

import static com.mohammad.replicanode.api.services.DatabaseService.getDatabase;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;

@Service
public class CollectionService {

    public Collection getCollection(String databaseName, String collectionName) {
        if (getCacheObject(collectionName) == null) {
            Collection currentCollection = getDatabase(databaseName).get(collectionName);
            CacheUtils.add(currentCollection.getCollectionName(),currentCollection);
            return currentCollection;
        }
        return (Collection) getCacheObject(collectionName);
    }
}
