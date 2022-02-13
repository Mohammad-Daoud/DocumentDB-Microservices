package com.mohammad.replicanode.schema.build;

public class SchemaCreator {
    private static final String OBJECT_ID = "_objectID";
    private static long index = 0;


    public static String create(String jsonObject){
        return "{\""+ OBJECT_ID + "\":"+index+",\n"+jsonObject+"}";
    }

    public static String create(String jsonObject, String indexProperty){
        return "{\""+ OBJECT_ID +"\":"+indexProperty+",\n"+jsonObject+"}";
    }

    public static String getObjectID() {
        return String.valueOf(index);
    }
}
