package com.mohammad.replicanode.index;


import com.mohammad.replicanode.utils.JSON;

public class Index {

    public static synchronized String createIndex(String jsonObject,String indexProperty){
        if (!JSON.isValidJson(jsonObject))
            throw new IllegalArgumentException("the json entered invalid !!");
        return JSON.getJsonObject(jsonObject,indexProperty);
    }

}
