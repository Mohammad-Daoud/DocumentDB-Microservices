package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {
    @Autowired
    MasterService controllerService;

    @GetMapping("/master/get-replicas")
    public boolean isThereChanges(){
        return controllerService.isThereChanges();
    }
}
