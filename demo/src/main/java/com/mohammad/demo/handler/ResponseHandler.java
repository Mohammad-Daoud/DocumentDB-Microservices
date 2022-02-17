package com.mohammad.demo.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


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


    public List<T> getResponse(String responseUrl){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        Object tempObject = REQUEST.doGetRequest(responseUrl);
        return MAPPER.convertValue(tempObject, new TypeReference<List<T>>() {});
    }

    public void postResponse(Map<String,Object> jsonMap,String responseUrl){
        REQUEST.setBasicAuthCredentials(USERNAME,PASSWORD);
        REQUEST.doPostRequest(jsonMap,responseUrl);
    }
}
