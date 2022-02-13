package com.mohammad.masternode.api.services;


import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.io.DirectoryCreator;
import com.mohammad.masternode.io.DirectoryRemover;
import com.mohammad.masternode.schema.Collection;
import com.mohammad.masternode.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.mohammad.masternode.io.DirectoryCreator.getMasterDir;
import static com.mohammad.masternode.io.DirectoryLoader.*;

@Service
public class DatabaseService {
    private static final List<Database> DATABASE_GROUP = new ArrayList<>();


    @Autowired
    private MasterNode masterNode;

    public synchronized void addDatabase(Database database) {
        Database newDatabase = new Database(database.getDatabaseName());
        DATABASE_GROUP.add(newDatabase);
        DirectoryCreator.getInstance().createDirectory(database.getDatabaseName());
        masterNode.notifyAllReplicas();
    }

    public synchronized void deleteDatabase(String databaseName) {
        DATABASE_GROUP.removeIf(database -> database.getDatabaseName().equals(databaseName));
        DirectoryRemover.getInstance().deleteDir(databaseName);
        masterNode.notifyAllReplicas();

    }

    public static Database getDatabase(String databaseName) {
        return getAllDatabases().stream()
                .filter(database -> database.getDatabaseName().equals(databaseName))
                .findFirst().orElseGet(() -> new Database(null));
    }

    public static List<Database> getAllDatabases() {
        return loadDatabases();
    }


    private static List<Database> loadDatabases() {
        List<Database> databaseGroup = new ArrayList<>();
        loadDirs(getMasterDir(), 0)
                .forEach(dir -> databaseGroup.add(new Database(dir.getName())));
        int databaseSize = databaseGroup.size();

        for (int i = 0; i < databaseSize; i++) {
            int databaseFinalIterator = i;
            int collectionFinalIterator = i;
            // this lambda to load the collection to specified database
            loadDirs(new File(getMasterDir()
                    + "/" + databaseGroup.get(i).getDatabaseName()), 0)
                    .forEach(collectionFolder -> {
                        databaseGroup.get(databaseFinalIterator)
                                .getCollectionGroup()
                                .put(collectionFolder.getName(), new Collection(collectionFolder.getName()));

                        // this lambda to load the documents to specified collection

                        loadDirs(new File(getMasterDir()
                                + "/"
                                + databaseGroup.get(collectionFinalIterator).getDatabaseName()
                                + "/"
                                + collectionFolder.getName()), 0)
                                .forEach(documentFolder -> {
                                    databaseGroup
                                            .get(databaseFinalIterator)
                                            .get(collectionFolder.getName())
                                            .add(documentFolder.getName());

                                    // this lambda to load the JSON Objects to specified document
                                    listFiles(
                                            getMasterDir()
                                                    + "/"
                                                    + databaseGroup.get(collectionFinalIterator).getDatabaseName()
                                                    + "/"
                                                    + collectionFolder.getName()
                                                    + "/"
                                                    + documentFolder.getName())
                                            .forEach(jsonFile ->
                                                    databaseGroup
                                                            .get(databaseFinalIterator)
                                                            .get(collectionFolder.getName())
                                                            .get(documentFolder.getName())
                                                            .add(readFile(getMasterDir()
                                                                    + "/"
                                                                    + databaseGroup.get(collectionFinalIterator).getDatabaseName()
                                                                    + "/"
                                                                    + collectionFolder.getName()
                                                                    + "/"
                                                                    + documentFolder.getName()
                                                                    + "/"
                                                                    + jsonFile)));
                                });

                    });

        }

        return databaseGroup;
    }


}
