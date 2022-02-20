package com.mohammad.masternode.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JSON {
    private static final Gson gson = new Gson();

    private JSON(){
        throw new AssertionError();
    }

    public static String toJson(Object json){
        return gson.toJson(json);
    }
    public static boolean isValidJson(String jsonObject) {
        try {
            gson.fromJson(jsonObject, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    public static String addJsonProperty(String jsonObject,String propertyKey,String propertyValue){
        JsonObject object = new Gson().fromJson(jsonObject, JsonObject.class);
        object.addProperty(propertyKey,propertyValue);
        return object.toString();
    }
    public static String getJsonObject(String jsonString, String indexProperty){
        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        return jsonObject.get(indexProperty).getAsString();
    }
}
