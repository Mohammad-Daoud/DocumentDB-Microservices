package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.index.Index;
import com.mohammad.masternode.io.DirectoryCreator;
import com.mohammad.masternode.io.DirectoryRemover;
import com.mohammad.masternode.schema.Document;
import com.mohammad.masternode.schema.create.SchemaCreator;
import com.mohammad.masternode.utils.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.mohammad.masternode.api.services.DatabaseService.getDatabase;
import static com.mohammad.masternode.schema.create.SchemaCreator.getObjectID;


@Service
public class DocumentService {


    public synchronized Document addDocument(String databaseName,
                                             String collectionName,
                                             Document document) {
        getDatabase(databaseName)
                .get(collectionName)
                .getDocumentGroup()
                .put(document.getDocumentName(), document);

        DirectoryCreator.getInstance().createDirectory(
                databaseName
                        + "/"
                        + collectionName
                        + "/"
                        + document.getDocumentName());
        MasterNode.getInstance().notifyAllReplicas();
        return document;
    }

    public synchronized void deleteDocument(String databaseName,
                                            String collectionName,
                                            String documentName) {

        getDatabase(databaseName)
                .get(collectionName)
                .getDocumentGroup()
                .remove(documentName);
        DirectoryRemover.getInstance().deleteDir(databaseName + "/" + collectionName + "/" + documentName);

        MasterNode.getInstance().notifyAllReplicas();
    }

    public synchronized void addJSON(String databaseName,
                                     String collectionName,
                                     String documentName,
                                     Map<String, Object> json,
                                     String index) {

        String realIndex = Index.createIndex(JSON.toJson(json), index);
        getDatabase(databaseName)
                .get(collectionName)
                .get(documentName)
                .add(JSON.toJson(json), realIndex);

        DirectoryCreator.getInstance().writeFile(
                databaseName
                        + "/"
                        + collectionName
                        + "/"
                        + documentName
                        + "/"
                        + realIndex, SchemaCreator.create(JSON.toJson(json), realIndex));

        MasterNode.getInstance().notifyAllReplicas();
    }

    public synchronized void addJSON(String databaseName,
                                     String collectionName,
                                     String documentName,
                                     Map<String, Object> json) {

        getDatabase(databaseName).get(collectionName).get(documentName).add(JSON.toJson(json));
        DirectoryCreator.getInstance().writeFile(
                databaseName
                        + "/"
                        + collectionName
                        + "/"
                        + documentName
                        + "/"
                        + getObjectID(), JSON.toJson(json));

        MasterNode.getInstance().notifyAllReplicas();
    }

    public synchronized void deleteJSON(String databaseName,
                                        String collectionName,
                                        String documentName,
                                        String jsonIndex) {
        DirectoryRemover.getInstance().deleteFile(databaseName + "/" + collectionName + "/" + documentName + "/" + jsonIndex);
        MasterNode.getInstance().notifyAllReplicas();
    }


}
