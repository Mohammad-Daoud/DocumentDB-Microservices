package com.mohammad.masternode.api.services;


import com.mohammad.masternode.cache.LRUCache;
import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.io.DirectoryCreator;
import com.mohammad.masternode.io.DirectoryRemover;
import com.mohammad.masternode.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    private static final List<Database> DATABASE_GROUP = new ArrayList<>();
    private static final LRUCache<String, Object> GLOBAL_CACHE = new LRUCache<>(100);

    @Autowired
    private MasterNode masterNode;

    public synchronized Database addDatabase(Database database) {
        Database newDatabase = new Database(database.getDatabaseName());
        DATABASE_GROUP.add(newDatabase);
        DirectoryCreator.getInstance().createDirectory(database.getDatabaseName());
        GLOBAL_CACHE.put(database.getDatabaseName(), database);
        masterNode.notifyAllReplicas();
        return newDatabase;
    }

    public synchronized void deleteDatabase(String databaseName) {
        GLOBAL_CACHE.evict(databaseName);

        DATABASE_GROUP.removeIf(database -> database.getDatabaseName().equals(databaseName));

        DirectoryRemover.getInstance().deleteDir(databaseName);
        masterNode.notifyAllReplicas();

    }

    public static Database getDatabase(String databaseName) {
        if (GLOBAL_CACHE.get(databaseName) == null)
            return DATABASE_GROUP.stream()
                    .filter(d -> d.getDatabaseName().equals(databaseName))
                    .findFirst().orElseGet(() -> new Database(null));
        return (Database) GLOBAL_CACHE.get(databaseName);
    }

    public static LRUCache<String, Object> getGlobalCache() {
        return GLOBAL_CACHE;
    }
}
