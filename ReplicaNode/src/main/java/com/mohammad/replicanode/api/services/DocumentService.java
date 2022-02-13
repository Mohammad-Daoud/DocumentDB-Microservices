package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Document;
import com.mohammad.replicanode.utils.CacheUtils;
import org.springframework.stereotype.Service;

import static com.mohammad.replicanode.api.services.DatabaseService.getDatabase;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;

@Service
public class DocumentService {

    public Document getDocument(String databaseName, String collectionName, String documentName) {
        if (getCacheObject(documentName) == null) {
            Document currentDocument = getDatabase(databaseName)
                    .get(collectionName)
                    .get(documentName);
            CacheUtils.add(currentDocument.getDocumentName(),currentDocument);
        }
        return (Document) getCacheObject(documentName);
    }

    public String getJsonObject(String databaseName,
                                String collectionName,
                                String documentName,
                                String jsonIndex) {

        if (getCacheObject(jsonIndex) == null) {
            String currentJSON = getDatabase(databaseName).get(collectionName).get(documentName).get(jsonIndex);
            CacheUtils.add(jsonIndex,currentJSON);
        }

        return (String) getCacheObject(jsonIndex);
    }
}
