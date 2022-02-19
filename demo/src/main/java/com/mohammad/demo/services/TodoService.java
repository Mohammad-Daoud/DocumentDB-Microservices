package com.mohammad.demo.services;

import com.mohammad.demo.exception.NotFoundException;
import com.mohammad.demo.handler.ResponseHandler;
import com.mohammad.demo.model.todos.Todo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {

    private final ResponseHandler<Todo> TODO_RESPONSE = new ResponseHandler<>();

    public List<Todo> retrieveUserTodos(String username) {
        return new ArrayList<>(TODO_RESPONSE.getResponse("read/get-doc/todoDB/users-todos?" + "name=" + username + "Todo"));
    }

    public void addTodo(String username, Map<String, Object> jsonMap) {
        TODO_RESPONSE.postResponse(jsonMap,
                "/master/add-json/todoDB/users-todos/" + username + "Todo/id");
    }

    public void deleteTodo(String username, int id) {
        TODO_RESPONSE.deleteResponse("/master/delete-json/todoDB/users-todos/" + username + "Todo?index=" + id);
    }

    public int addID(String username, int id) {
        int tempId = id;
        boolean isIDExist = retrieveUserTodos(username).stream().anyMatch(todoID -> todoID.getId() == tempId);
        if (isIDExist)
            return addID(username, ++id);

        return id;
    }

    public Map<String, Object> todoCreator(String username, String description) {
        Map<String, Object> addedTodo = new Hashtable<>();
        addedTodo.put("id", addID(username, 1));
        addedTodo.put("desc", description);
        addedTodo.put("status", false);
        return addedTodo;
    }





    public void setDoneStatus(String username, int id) {
        Todo tempTodo = getTodo(username,id);
        if (tempTodo == null)
            throw new NotFoundException("user not found");
        Map<String,Object> editedTodo = todoCreator(tempTodo.getDesc(),tempTodo.getId());
        addTodo(username,editedTodo);
    }

    private Todo getTodo(String username, int id) {
        return retrieveUserTodos(username)
                .stream()
                .filter(todoID -> todoID.getId() == id)
                .findFirst().orElse(null);
    }

    private Map<String, Object> todoCreator(String description,int id) {
        Map<String, Object> addedTodo = new Hashtable<>();
        addedTodo.put("id", id);
        addedTodo.put("desc", description);
        addedTodo.put("status", true);
        return addedTodo;
    }
}
