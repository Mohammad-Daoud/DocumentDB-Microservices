package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.services.MasterService;
import com.mohammad.masternode.cluster.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasterController {
    @Autowired
    MasterService controllerService;

    @GetMapping("/master/is-change")
    public boolean isThereChanges(){
        return controllerService.isThereChanges();
    }

    @GetMapping("/master/get-replicas")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Observer> getReplicaGroup(){
        return controllerService.getReplicaGroup();
    }
}
