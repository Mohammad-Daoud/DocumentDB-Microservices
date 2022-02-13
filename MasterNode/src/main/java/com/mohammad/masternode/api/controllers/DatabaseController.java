package com.mohammad.masternode.api.controllers;

import com.mohammad.masternode.api.services.DatabaseService;
import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DatabaseController {

    @Autowired
    private DatabaseService service;


    @PostMapping("/master/add-database")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public synchronized void addDatabase(@RequestBody Database database){
         service.addDatabase(database);

    }

    @DeleteMapping(path = "/master/delete/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void deleteDatabase(@PathVariable String databaseName){
        service.deleteDatabase(databaseName);

    }


}


