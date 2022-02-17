package com.mohammad.replicanode.cluster;

import org.springframework.web.client.RestTemplate;


public class Replica extends Thread {

    private static final String URI = "http://localhost:8000/master/is-change";

    private Replica() {
    }

    public static Replica create() {
        return new Replica();
    }


    public boolean isThereMasterChanges() {
        RestTemplate restTemplate = new RestTemplate();
        return Boolean.TRUE.equals(restTemplate.getForObject(URI, Boolean.class));
    }

    @Override
    public void run() {
        isThereMasterChanges();
    }
}
