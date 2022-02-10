package com.mohammad.replicanode.schema.build;

public class SchemaBuilder {
    private static long id = 0;
    private static String _objectID = "_objectID:";


    public static String build(String jsonObject){
        return "{\""+_objectID+idCounter()+"\":\n"+jsonObject+"}";
    }
    public static String build(String jsonObject, String indexProperty){
        return "{\""+_objectID+indexProperty+"\":\n"+jsonObject+"}";
    }


    public static String idCounter() {
        return String.valueOf(++id);
    }

    public static String getId() {
        return String.valueOf(id);
    }
}
