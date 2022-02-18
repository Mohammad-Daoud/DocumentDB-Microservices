package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.ResponseHandler.ResponseStatus;
import com.mohammad.masternode.api.services.CollectionService;
import com.mohammad.masternode.schema.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@EnableAsync
@RestController
public class CollectionController {

    @Autowired
    private CollectionService service;
    @Autowired
    private ResponseStatus<Object> status;


    @PostMapping("/master/add-collection/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addCollection(@PathVariable String databaseName,
                              @RequestBody Collection collection) {
        Collection addedCollection = service.addCollection(databaseName, collection);
        return status.getResponseStatus(addedCollection.getCollectionName(),"/{name}");
    }

    @DeleteMapping("/master/delete-collection/{databaseName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCollection(@PathVariable String databaseName,
                                 @RequestParam String collectionName) {
        service.deleteCollection(databaseName, collectionName);
    }


}
