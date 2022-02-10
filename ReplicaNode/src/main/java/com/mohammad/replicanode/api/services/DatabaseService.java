package com.mohammad.replicanode.api.services;

import com.mohammad.replicanode.schema.Database;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mohammad.replicanode.io.DirectoryLoader.*;
import static com.mohammad.replicanode.utils.CacheUtils.getCacheObject;
import static com.mohammad.replicanode.utils.CacheUtils.putIntoGlobalCache;

@Service
public class DatabaseService {
    public static List<Database> getAllDatabases() {
        List<Database> databaseGroup = new ArrayList<>();
        return loadDatabases(databaseGroup);
    }
    public static Database getDatabase(String databaseName) {
        if (getCacheObject(databaseName) == null)
            return getAllDatabases().stream()
                    .filter(d -> d.getDatabaseName().equals(databaseName))
                    .findFirst().orElseGet(() -> new Database(null));
        
        return (Database) getCacheObject(databaseName);
    }


    private static List<Database> loadDatabases(List<Database> databaseGroup) {
        loadDirs(MASTER_DIR, 0)
                .forEach(dir -> databaseGroup.add(new Database(dir)));

        loadDirs(MASTER_DIR, 1)
                .forEach(dir -> {
                    AtomicInteger file = new AtomicInteger(0);
                    databaseGroup.get(file.get()).add(loadDirs(MASTER_DIR, 1).get(file.get()));
                    file.getAndIncrement();
                });
        loadDirs(MASTER_DIR, 1)
                .forEach(dir -> {
                    AtomicInteger file = new AtomicInteger(0);
                    databaseGroup.get(file.get()).get(loadDirs(MASTER_DIR, 1).get(file.get()))
                            .add(listFiles(MASTER_DIR.toString()).get(file.get()));
                    file.getAndIncrement();
                });
        loadDirs(MASTER_DIR, 1)
                .forEach(dir -> {
                    AtomicInteger file = new AtomicInteger(0);
                        databaseGroup.get(file.get()).get(loadDirs(MASTER_DIR, 1).get(file.get()))
                                .get(listFiles(MASTER_DIR.toString()).get(file.get()))
                                .add(readFile(MASTER_DIR
                                        + "/"
                                        + loadDirs(MASTER_DIR, 0).get(file.get())
                                        + "/"
                                        + loadDirs(MASTER_DIR, 1).get(file.get())
                                        + "/"
                                        + listFiles(MASTER_DIR.toString()).get(file.get())));

                    file.getAndIncrement();
                });

        putIntoGlobalCache(databaseGroup);
        return databaseGroup;
    }


}