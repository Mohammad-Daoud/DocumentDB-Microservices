package com.mohammad.masternode.api.controllers;

import com.mohammad.masternode.api.ResponseHandler.ResponseStatus;
import com.mohammad.masternode.api.services.UserService;
import com.mohammad.masternode.users.Role;
import com.mohammad.masternode.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    private ResponseStatus<Object> status;

    @PostMapping("/master/add-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> addUser(@RequestBody String username,
                                          @RequestBody String password,
                                          @RequestBody Role role) {
        User addedUser = service.addUser(username, password, role);
        return status.getResponseStatus(addedUser.getUsername(), "/{username}");
    }

    @DeleteMapping("/master/delete-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@RequestBody String username) {
        service.deleteUser(username);
    }


    @PostMapping("/master/change-user-password")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> changePassword(@RequestBody String username,
                                                 @RequestBody String password) {
        User changedUser = service.changeUserPassword(username, password);

        return status.getResponseStatus(changedUser.getUsername(), "/{username}");
    }

    @PostMapping("/master/change-user-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> changeRole(@RequestBody String username,
                                             @RequestBody Role role) {
        User changedUser = service.changeUserRole(username, role);
        return status.getResponseStatus(changedUser.getUsername(), "/{username}");
    }

    @PostMapping("/master/change-user-username")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> changeUsername(@RequestBody String oldUsername,
                                                 @RequestBody String newUsername) {
        User changedUser = service.changeUserUsername(oldUsername, newUsername);
        return status.getResponseStatus(changedUser.getUsername(), "/{username}");
    }

}
