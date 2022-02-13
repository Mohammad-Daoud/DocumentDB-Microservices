package com.mohammad.masternode.api.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohammad.masternode.exception.NotFoundException;
import com.mohammad.masternode.exception.UserConflictException;
import com.mohammad.masternode.io.DirectoryCreator;
import com.mohammad.masternode.users.Role;
import com.mohammad.masternode.users.User;
import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.JSON;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class UserService {
    private final List<User> USER_GROUP = new ArrayList<>();
    private final File USER_FILE
            = new File("C:/Users/mdss4/Documents/Atypon/DocumentDB/MasterNode/storage/admin/users/users-info.json");
    private final AppLogger LOGGER =  AppLogger.create("User Log :");



    public List<User> getAllUser() {
        return loadUsers(USER_GROUP);
    }

    private List<User> loadUsers(List<User> userGroup) {
        try (FileReader fileReader = new FileReader(USER_FILE)) {
            userGroup = new Gson().fromJson(fileReader, new TypeToken<List<User>>() {
            }.getType());
        } catch (IOException e) {
            LOGGER.logError(e);
        }
        return userGroup;
    }


    public synchronized void addUser(String username,
                                     String password, Role role) {
        if (isUserExist(username))
            throw new UserConflictException("the user exist");

        USER_GROUP.add(new User.UserBuilder()
                .username(username)
                .password(password)
                .role(role)
                .build());

        DirectoryCreator.getInstance().overrideWriteFile(USER_FILE.getPath(), USER_GROUP.toString());
    }


    public synchronized void deleteUser(String username) {
        List<User> tempUserGroup = getAllUser();
        if (isUserExist(username)) {
            tempUserGroup.removeIf(usr -> usr.getUsername().equals(username));
            DirectoryCreator.getInstance().overrideWriteFile(USER_FILE.getAbsolutePath(), tempUserGroup.toString());
        } else {
            throw new NotFoundException("USER NOT FOUND !");
        }
    }

    public User getUser(String username) {
        if (USER_GROUP.size() > 0)
            return USER_GROUP.stream()
                    .filter(usr -> usr.getUsername().equals(username))
                    .findFirst().orElseGet(() -> new User.UserBuilder().build());

        return getAllUser().stream().filter(usr -> usr.getUsername().equals(username))
                .findFirst().orElseGet(() -> new User.UserBuilder().build());
    }

    public boolean isUserExist(String username) {
        boolean isFound = false;
        try (Scanner scanner = new Scanner(new FileReader(USER_FILE))) {
            while (scanner.hasNextLine() && !isFound) {
                isFound = scanner.nextLine().contains(username);
            }
        } catch (IOException e) {
            LOGGER.logError(e);
        }

        return isFound;
    }

    public synchronized void changeUserPassword(String username, String newPassword) {
        List<User> tempUsersGroup = getAllUser();
        if (isUserExist(username)) {
            getUser(username).setPassword(newPassword);
            DirectoryCreator.getInstance().overrideWriteFile(USER_FILE.getAbsolutePath(), JSON.toJson(tempUsersGroup));
        } else {
            throw new NotFoundException("USER NOT FOUND !");
        }

    }

    public synchronized void changeUserRole(String username, Role role) {
        List<User> tempUsersGroup = getAllUser();
        if (isUserExist(username)) {
            getUser(username).setRole(role);
            DirectoryCreator.getInstance().overrideWriteFile(USER_FILE.getAbsolutePath(), JSON.toJson(tempUsersGroup));
        } else {
            throw new NotFoundException("USER NOT FOUND !");
        }
    }

    public synchronized void changeUserUsername(String oldUsername, String newUsername) {
        List<User> tempUsersGroup = getAllUser();
        if (isUserExist(oldUsername)) {
            getUser(oldUsername).setUsername(newUsername);
            DirectoryCreator.getInstance().overrideWriteFile(USER_FILE.getAbsolutePath(), JSON.toJson(tempUsersGroup));
        } else {
            throw new NotFoundException("USER NOT FOUND !");
        }
    }

}

