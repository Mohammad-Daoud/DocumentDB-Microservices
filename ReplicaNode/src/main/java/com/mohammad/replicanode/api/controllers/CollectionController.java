package com.mohammad.replicanode.api.controllers;

import com.mohammad.replicanode.api.services.CollectionService;
import com.mohammad.replicanode.schema.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {
    @Autowired
   private CollectionService service;

    @GetMapping("/read/get-collection/{databaseName}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public Collection getCollection(@PathVariable String databaseName,
                                    @RequestParam String name) {
        return service.getCollection(databaseName, name);
    }

}
