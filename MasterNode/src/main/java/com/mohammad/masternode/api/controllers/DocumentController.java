package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.services.DocumentService;
import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.schema.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService service;


    @PostMapping("/master/add-doc/{databaseName}/{collectionName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void putDocument(@PathVariable String databaseName,
                            @PathVariable String collectionName,
                            @RequestBody Document document) {
        service.addDocument(databaseName, collectionName, document);


    }

    @DeleteMapping("/master/delete-doc/{databaseName}/{collectionName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteDocument(@PathVariable String databaseName,
                               @PathVariable String collectionName,
                               @RequestParam String documentName) {
        service.deleteDocument(databaseName, collectionName, documentName);

    }

    @PostMapping("/master/add-json/{databaseName}/{collectionName}/{documentName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void putJsonObject(@PathVariable String databaseName,
                              @PathVariable String collectionName,
                              @PathVariable String documentName,
                              @RequestBody Map<String, Object> json) {
        service.addJSON(databaseName, collectionName, documentName, json);

    }

    @PostMapping("/master/add-json/{databaseName}/{collectionName}/{documentName}/{index}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void putJsonObject(@PathVariable String databaseName,
                              @PathVariable String collectionName,
                              @PathVariable String documentName,
                              @PathVariable String index,
                              @RequestBody Map<String, Object> json) {
        service.addJSON(databaseName, collectionName, documentName, json, index);

    }


    @DeleteMapping("/master/delete-json/{databaseName}/{collectionName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteJsonObject(@PathVariable String databaseName,
                               @PathVariable String collectionName,
                               @PathVariable String documentName,
                               @PathVariable String index) {

        service.deleteJSON(databaseName, collectionName, documentName,index);

    }
}