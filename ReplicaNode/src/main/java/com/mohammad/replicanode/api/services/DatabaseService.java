package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Collection;
import com.mohammad.replicanode.schema.Database;
import com.mohammad.replicanode.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.mohammad.replicanode.io.FileManager.*;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;

@Service
public class DatabaseService {
    public static List<Database> getAllDatabases() {
        return loadDatabases();
    }

    public static Database getDatabase(String databaseName) {
        if (getCacheObject(databaseName) == null) {
            Database currentDatabase = getAllDatabases().stream()
                    .filter(d -> d.getDatabaseName().equals(databaseName))
                    .findFirst().orElseGet(() -> new Database(null));

            CacheUtils.add(currentDatabase.getDatabaseName(), currentDatabase);
            return currentDatabase;
        }
        return (Database) getCacheObject(databaseName);
    }


    private static List<Database> loadDatabases() {
        List<Database> databaseGroup = new ArrayList<>();
        loadDirs(getDatabaseDir(), 0)
                .forEach(dir -> databaseGroup.add(new Database(dir.getName())));
        int databaseSize = databaseGroup.size();

        for (int i = 0; i < databaseSize; i++) {
            int databaseFinalIterator = i;
            int collectionFinalIterator = i;
            // this lambda to load the collection to specified database
            loadDirs(new File(getDatabaseDir()
                    + "/" + databaseGroup.get(i).getDatabaseName()), 0)
                    .forEach(collectionFolder -> {
                        databaseGroup.get(databaseFinalIterator)
                                .getCollectionGroup()
                                .put(collectionFolder.getName(), new Collection(collectionFolder.getName()));

                        // this lambda to load the documents to specified collection

                        loadDirs(new File(getDatabaseDir()
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
                                            getDatabaseDir()
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
                                                            .add(readFile(getDatabaseDir()
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

        CacheUtils.addALlIntoGlobalCache(databaseGroup);
        return databaseGroup;
    }


}