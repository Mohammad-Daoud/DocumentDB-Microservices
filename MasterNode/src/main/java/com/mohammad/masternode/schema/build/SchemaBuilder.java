package com.mohammad.masternode.schema.build;

import com.mohammad.masternode.utils.JSON;

import static com.mohammad.masternode.utils.JSON.addJsonProperty;

public class SchemaBuilder {
    private static long index = 0;
    private static String _objectID = "_objectID";


    public static String build(String jsonObject){
        return addJsonProperty(jsonObject,_objectID,String.valueOf(index));
    }
    public static String build(String jsonObject, String indexProperty){
        return addJsonProperty(jsonObject,_objectID,indexProperty);
    }


    public static String indexCounter() {
        return String.valueOf(++index);
    }

    public static String getObjectID() {
        return String.valueOf(index);
    }
}
