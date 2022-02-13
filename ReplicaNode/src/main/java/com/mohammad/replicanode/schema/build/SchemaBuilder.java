package com.mohammad.replicanode.schema.build;

public class SchemaBuilder {
    private static long index = 0;
    private static String _objectID = "_objectID:";


    public static String build(String jsonObject){
        return "{\""+_objectID+ index +"\":\n"+jsonObject+"}";
    }
    public static String build(String jsonObject, String indexProperty){
        return "{\""+_objectID+indexProperty+"\":\n"+jsonObject+"}";
    }


    public static String indexCounter() {
        return String.valueOf(++index);
    }

    public static String getObjectID() {
        return String.valueOf(index);
    }
}
