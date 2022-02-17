package com.mohammad.masternode.api.ResponseHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class ResponseStatus<T> {

    public ResponseEntity<T> getResponseStatus(Object object,String pathComponents){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(pathComponents)
                .buildAndExpand(object)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
