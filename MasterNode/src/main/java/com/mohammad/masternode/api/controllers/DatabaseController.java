package com.mohammad.masternode.api.controllers;

import com.mohammad.masternode.api.ResponseHandler.ResponseStatus;
import com.mohammad.masternode.api.services.DatabaseService;
import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@EnableAsync
@RestController
public class DatabaseController {

    @Autowired
    private DatabaseService service;

    @Autowired
    private ResponseStatus<Object> status;


    @PostMapping("/master/add-database")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized ResponseEntity<Object> addDatabase(@RequestBody Database database) {
        Database addedDataBase = service.addDatabase(database);
        return status.getResponseStatus(addedDataBase.getDatabaseName(),"/{name}");
    }

    @DeleteMapping(path = "/master/delete/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteDatabase(@PathVariable String databaseName) {
        service.deleteDatabase(databaseName);
    }


}


