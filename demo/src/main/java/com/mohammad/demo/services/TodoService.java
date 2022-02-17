package com.mohammad.demo.services;

import com.mohammad.demo.handler.ResponseHandler;
import com.mohammad.demo.model.todos.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    private final ResponseHandler<Todo> TODO_RESPONSE = new ResponseHandler<>();


    public List<Todo> retrieveUserTodos(String username) {
        List<Todo> todoGroup = new ArrayList<>();
        todoGroup = TODO_RESPONSE.getResponse("read/get-doc/todoDB/users-todos?" + "name=" + username + "Todo");
        return todoGroup;
    }

    public void addTodo(String username, Map<String, Object> jsonMap) {
        TODO_RESPONSE.postResponse(jsonMap,
                "/master/add-json/todoDB/users-todos/" + username + "Todo/id");
    }

}
