package com.mohammad.masternode.api.controllers;

import com.mohammad.masternode.api.services.UserService;
import com.mohammad.masternode.users.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping ("/master/add-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void addUser(@RequestBody String username,
                                     @RequestBody String password,
                                     @RequestBody Role role) {
        service.addUser(username, password, role);
    }

    @DeleteMapping("/master/delete-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void deleteUser(@RequestBody String username) {
        service.deleteUser(username);
    }


    @PostMapping("/master/change-user-password")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void changePassword(@RequestBody String username,
                                            @RequestBody String password) {
        service.changeUserPassword(username, password);
    }

    @PostMapping("/master/change-user-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void changeRole(@RequestBody String username,
                                        @RequestBody Role role) {
        service.changeUserRole(username, role);
    }

    @PostMapping("/master/change-user-username")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public synchronized void changeUsername(@RequestBody String oldUsername,
                                            @RequestBody String newUsername) {
        service.changeUserUsername(oldUsername, newUsername);
    }

}
