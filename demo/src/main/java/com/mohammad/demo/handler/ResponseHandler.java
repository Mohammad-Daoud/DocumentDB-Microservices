package com.mohammad.demo.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mohammad.demo.model.todos.Todo;


import java.util.List;
import java.util.Map;

public class ResponseHandler <T>  {
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final RequestHandler REQUEST = RequestHandler.create();

    private final String USERNAME = "root";
    private final String PASSWORD = "root" ;

    public T getResponse(String responseUrl, Class<T> mappingClass){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        Object tempObject = REQUEST.doGetRequest(responseUrl);
        return MAPPER.convertValue(tempObject, mappingClass);
    }


    public List<Todo> getResponse(String responseUrl){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        Object tempObject = REQUEST.doGetRequest(responseUrl);
        return MAPPER.convertValue(tempObject, new TypeReference<List<Todo>>() {});
    }

    public void postResponse(Map<String,Object> jsonMap,String responseUrl){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        REQUEST.doPostRequest(jsonMap,responseUrl);
    }

    public void deleteResponse(String responseUrl){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        REQUEST.doDeleteRequest(responseUrl);
    }
}
