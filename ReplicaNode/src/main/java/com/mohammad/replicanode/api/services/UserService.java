package com.mohammad.replicanode.api.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohammad.replicanode.users.User;
import com.mohammad.replicanode.utils.AppLogger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


import static com.mohammad.replicanode.io.FileManager.getUsersDir;


@Service
public class UserService {

    private static final List<User> userGroup = new ArrayList<>();


    private final File USER_FILE = new File(getUsersDir()+"/storage/admin/users/users-info.json");
    private final AppLogger logger =  AppLogger.create("UserLog");



    public List<User> getAllUsers() {
        return loadUsers(userGroup);
    }

    private List<User> loadUsers(List<User> userGroup) {
        try (FileReader fileReader = new FileReader(USER_FILE)) {
            userGroup = new Gson().fromJson(fileReader, new TypeToken<List<User>>() {
            }.getType());
            return userGroup;
        } catch (Exception e) {
            logger.logError(e);
            return null;
        }
    }
    public User getUser(String username) {
        if (userGroup.size() > 0)
        return userGroup.stream()
                .filter(usr -> usr.getUsername().equals(username))
                .findFirst().orElseGet(() -> new User.UserBuilder().build());

        return getAllUsers().stream().filter(usr -> usr.getUsername().equals(username))
                .findFirst().orElseGet(() -> new User.UserBuilder().build());
    }



}
