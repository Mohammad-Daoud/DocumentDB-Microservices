package com.mohammad.replicanode.utils;

import org.springframework.web.client.RestTemplate;


public class ReplicaUtils {
    private static final String URI = "http://localhost:8000/master/get-replicas";
    private ReplicaUtils() {
        throw new AssertionError();
    }

    public static boolean isThereMasterChanges() {
        RestTemplate restTemplate = new RestTemplate();
        Boolean masterResponse = restTemplate.getForObject(URI, Boolean.class);
        return masterResponse;
    }
}
