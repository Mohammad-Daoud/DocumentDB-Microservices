package com.mohammad.masternode.schema.create;

import static com.mohammad.masternode.utils.JSON.addJsonProperty;

public class SchemaCreator {
    private final static String OBJECT_ID = "_objectID";
    private static long index = 0;


    public static String create(String jsonObject) {
        return addJsonProperty(jsonObject, OBJECT_ID, String.valueOf(index));
    }

    public static String create(String jsonObject, String indexProperty) {
        return addJsonProperty(jsonObject, OBJECT_ID, indexProperty);
    }


    public static String indexCounter() {
        return String.valueOf(++index);
    }

    public static String getObjectID() {
        return String.valueOf(index);
    }
}
