package com.mohammad.masternode.index;


import com.mohammad.masternode.utils.JSON;

public class Index {

    public static String createIndex(String jsonObject, String indexProperty) {
        if (JSON.isValidJson(jsonObject))
            return JSON.getJsonObject(jsonObject, indexProperty);

        throw new IllegalArgumentException("the json entered invalid !!");
    }


}
