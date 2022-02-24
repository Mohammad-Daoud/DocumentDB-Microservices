package com.mohammad.replicanode.api.controllers;


import com.mohammad.replicanode.api.services.ReplicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplicaController {
    @Autowired
    private ReplicaService service;
    @Autowired
    private ServerProperties replicaProperties;

    @GetMapping("/read/clear-cache/{isChange}")
    public String clearCacheForChange(@PathVariable Boolean isChange){
        int port = replicaProperties.getPort();
        service.cleanCacheForChange(isChange);
        return "Replica with port "+ port + " has updated successfully " ;
    }
}
