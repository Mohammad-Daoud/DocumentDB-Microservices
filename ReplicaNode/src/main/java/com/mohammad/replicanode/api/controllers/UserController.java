package com.mohammad.replicanode.api.controllers;

import com.mohammad.replicanode.api.services.UserService;
import com.mohammad.replicanode.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/read/get-all-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping("/read/get-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User getUser(@RequestParam String username){
        return service.getUser(username);
    }

}
