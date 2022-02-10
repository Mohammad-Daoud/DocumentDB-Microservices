package com.mohammad.masternode.schema;


import java.util.HashMap;

public class Database implements SchemaOperation {

    private  String databaseName;
    private  HashMap<String, Collection> collectionGroup = new HashMap<>();

    public Database(){

    }
    public Database(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void add(String collectionName) {
        Collection tempCollection = new Collection(collectionName);
        collectionGroup.put(collectionName, tempCollection);
    }

    @Override
    public Collection get(String collectionName) {
        return collectionGroup.get(collectionName);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public HashMap<String, Collection> getCollectionGroup() {
        return collectionGroup;
    }

    @Override
    public String toString() {
        return "{\n" +
                databaseName +
                "\n}";
    }
}
