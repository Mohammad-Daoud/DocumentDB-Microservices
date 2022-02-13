package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Document;
import org.springframework.stereotype.Service;

import static com.mohammad.replicanode.api.services.DatabaseService.getDatabase;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;

@Service
public class DocumentService {

    public Document getDocument(String databaseName, String collectionName, String documentName) {
        if (getCacheObject(documentName) == null)
            return getDatabase(databaseName)
                    .get(collectionName)
                    .get(documentName);

        return (Document) getCacheObject(documentName);
    }

    public String getJsonObject(String databaseName,
                                String collectionName,
                                String documentName,
                                String jsonIndex) {

        if (getCacheObject(jsonIndex) == null)
            return getDatabase(databaseName).get(collectionName).get(documentName).get(jsonIndex);
        return (String) getCacheObject(jsonIndex);
    }
}
