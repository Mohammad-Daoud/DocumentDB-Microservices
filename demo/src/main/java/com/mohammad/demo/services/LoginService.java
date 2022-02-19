package com.mohammad.demo.services;

import com.mohammad.demo.handler.ResponseHandler;
import com.mohammad.demo.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ResponseHandler<User> USER_RESPONSE = new ResponseHandler<>();

    public boolean isValidUser(String username, String password) {
        User currentUser = USER_RESPONSE.getResponse("read/get-json/todoDB/users/users-details?index=" + username,
                User.class);
         if (currentUser == null)
             return false;
        return currentUser.getPassword().equals(password);
    }
}
