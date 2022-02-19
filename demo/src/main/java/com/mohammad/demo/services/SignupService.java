package com.mohammad.demo.services;

import com.mohammad.demo.handler.ResponseHandler;
import com.mohammad.demo.model.user.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignupService {

    private final ResponseHandler<User> USER_RESPONSE = new ResponseHandler<>();

    public Boolean isUserExist(String username) {
        User currentUser = USER_RESPONSE.getResponse(
                "read/get-json/todoDB/users/users-details?index=" + username,
                User.class);
        return currentUser != null;
    }

    public void registerNewUser(String username, String password) {
        Map<String, Object> userDetailsMap = userHelper(username, password);
        Map<String,Object> createTodoMap = new HashMap<>();
        createTodoMap.put("documentName",username+"Todo");
        USER_RESPONSE.postResponse(userDetailsMap, "/master/add-json/todoDB/users/users-details/username");
        USER_RESPONSE.postResponse(createTodoMap,"/master/add-doc/todoDB/users-todos");
    }

    private Map<String,Object> userHelper(String username,String password){
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("username", username);
        jsonMap.put("password", password);
        jsonMap.put("userTodo", username + "Todo");

        return jsonMap;
    }
}
