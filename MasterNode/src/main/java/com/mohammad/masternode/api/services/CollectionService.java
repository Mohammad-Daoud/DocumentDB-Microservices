package com.mohammad.masternode.api.services;


import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.io.DirectoryCreator;
import com.mohammad.masternode.io.DirectoryRemover;
import com.mohammad.masternode.schema.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mohammad.masternode.api.services.DatabaseService.getDatabase;


@Service
public class CollectionService {


    public synchronized Collection addCollection(String databaseName, Collection collection) {
        getDatabase(databaseName).add(collection.getCollectionName());
        DirectoryCreator
                .getInstance()
                .createDirectory(databaseName
                + "/"
                + collection.getCollectionName());

        MasterNode.getInstance().notifyAllReplicas();
        return collection;
    }

    public synchronized void deleteCollection(String databaseName, String collectionName) {
        getDatabase(databaseName).getCollectionGroup().remove(collectionName);
        DirectoryRemover.getInstance().deleteDir(databaseName + "/" + collectionName);
        MasterNode.getInstance().notifyAllReplicas();    }



}
