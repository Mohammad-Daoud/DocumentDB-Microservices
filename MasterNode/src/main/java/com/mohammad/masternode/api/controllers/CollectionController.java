package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.services.CollectionService;
import com.mohammad.masternode.schema.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@EnableAsync
@RestController
public class CollectionController {

    @Autowired
    private CollectionService service;

    @PostMapping("/master/add-collection/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addCollection(@PathVariable String databaseName,
                              @RequestBody Collection collection) {
        service.addCollection(databaseName, collection);
    }

    @DeleteMapping("/master/delete-collection/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCollection(@PathVariable String databaseName,
                                 @RequestParam String collectionName) {
        service.deleteCollection(databaseName, collectionName);
    }


}
