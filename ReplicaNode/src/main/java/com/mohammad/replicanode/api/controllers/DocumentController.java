package com.mohammad.replicanode.api.controllers;


import com.mohammad.replicanode.api.services.DocumentService;
import com.mohammad.replicanode.schema.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService service;
    @GetMapping("/read/get-doc/{databaseName}/{collectionName}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String getDocument(@PathVariable String databaseName,
                                @PathVariable String collectionName,
                                @RequestParam String name) {

        return service.getDocument(databaseName, collectionName, name).toString();
    }

    @GetMapping("/read/get-json/{databaseName}/{collectionName}/{documentName}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String getJson(@PathVariable String databaseName,
                          @PathVariable String collectionName,
                          @PathVariable String documentName,
                          @RequestParam String index) {
        return service.getJsonObject(databaseName, collectionName, documentName, index);
    }
}
