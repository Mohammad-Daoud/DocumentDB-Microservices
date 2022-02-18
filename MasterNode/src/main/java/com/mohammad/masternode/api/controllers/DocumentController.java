package com.mohammad.masternode.api.controllers;


import com.mohammad.masternode.api.ResponseHandler.ResponseStatus;
import com.mohammad.masternode.api.services.DocumentService;
import com.mohammad.masternode.schema.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService service;
    @Autowired
    private ResponseStatus<Object> status;

    @PostMapping("/master/add-doc/{databaseName}/{collectionName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addDocument(@PathVariable String databaseName,
                                              @PathVariable String collectionName,
                                              @RequestBody Document document) {
        Document addedDoc = service.addDocument(databaseName, collectionName, document);
        return status.getResponseStatus(addedDoc.getDocumentName(), "/{name}");


    }

    @PostMapping("/master/add-json/{databaseName}/{collectionName}/{documentName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addJsonObject(@PathVariable String databaseName,
                                                @PathVariable String collectionName,
                                                @PathVariable String documentName,
                                                @RequestBody Map<String, Object> json) {
        service.addJSON(databaseName, collectionName, documentName, json);
        return status.getResponseStatus(json, "/{index}");
    }

    @PostMapping("/master/add-json/{databaseName}/{collectionName}/{documentName}/{index}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addJsonObject(@PathVariable String databaseName,
                                                @PathVariable String collectionName,
                                                @PathVariable String documentName,
                                                @PathVariable String index,
                                                @RequestBody Map<String, Object> json) {
        service.addJSON(databaseName, collectionName, documentName, json, index);
        return status.getResponseStatus(index, "/{index}");

    }

    @DeleteMapping("/master/delete-doc/{databaseName}/{collectionName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteDocument(@PathVariable String databaseName,
                               @PathVariable String collectionName,
                               @RequestParam String documentName) {
        service.deleteDocument(databaseName, collectionName, documentName);

    }


    @DeleteMapping("/master/delete-json/{databaseName}/{collectionName}/{documentName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteJsonObject(@PathVariable String databaseName,
                                 @PathVariable String collectionName,
                                 @PathVariable String documentName,
                                 @RequestParam String index) {

        service.deleteJSON(databaseName, collectionName, documentName, index);

    }
}
