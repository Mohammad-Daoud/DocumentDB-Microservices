package com.mohammad.demo.handler;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

class RequestHandler{

    private static final String BASE_URI = "http://localhost:8080/";
    private final RestTemplate REST_REQUEST;

    private RequestHandler(){
        REST_REQUEST = new RestTemplate();
    }
    public static RequestHandler create(){
        return new RequestHandler();
    }

    public void setBasicAuthCredentials(String username, String password){
        REST_REQUEST.getInterceptors().add(new BasicAuthenticationInterceptor(username,password));
    }

    public Object doGetRequest(String urlMethod){
        return REST_REQUEST.exchange(BASE_URI + urlMethod, HttpMethod.GET,null, Object.class).getBody();
    }

    public void doPostRequest(Map<String,Object> jsonMap, String urlMethod){
        REST_REQUEST.postForEntity(BASE_URI + urlMethod,jsonMap,Object.class);
    }

    public void doDeleteRequest(String urlMethod){
        REST_REQUEST.delete(BASE_URI+urlMethod);
    }

}
