package com.mohammad.replicanode.api.controllers;


import com.mohammad.replicanode.api.services.DatabaseService;
import com.mohammad.replicanode.exception.NotFoundException;
import com.mohammad.replicanode.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DatabaseController {

    @Autowired
    private DatabaseService service ;

    @GetMapping("/read/get-all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public List<Database> retrieveAllUsers() {
        return service.getAllDatabases();
    }

    @GetMapping (path = "/read/get-database")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public Database getDatabase(@RequestParam String databaseName){
        Database database = service.getDatabase(databaseName);
        if (database == null)
            throw new NotFoundException("database- "+databaseName);

        return database;
    }

}
