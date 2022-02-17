package com.mohammad.demo.services;

import com.mohammad.demo.handler.ResponseHandler;
import com.mohammad.demo.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ResponseHandler<User> USER_RESPONSE = new ResponseHandler<>();
    private static User currentUser;
    public boolean isValidUser(String username, String password) {
         currentUser = USER_RESPONSE.getResponse("read/get-json/todoDB/users/users-details?index=" + username,
                User.class);
        return currentUser.getPassword().equals(password);
    }

    public static String getUserTodo(){
        return currentUser.getUserTodo();
    }
}
