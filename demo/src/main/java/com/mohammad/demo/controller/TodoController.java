package com.mohammad.demo.controller;

import com.mohammad.demo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


@Controller
@SessionAttributes("username")
public class TodoController {
    @Autowired
    TodoService service;


    @RequestMapping(value = "/user-todos", method = RequestMethod.GET)
    public String viewUserTodos(ModelMap model) {
        String username = (String) model.get("username");
        model.addAttribute("userTodos", service.retrieveUserTodos(username));
        return "user-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String viewTodoPage() {
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model,
                          @RequestParam String desc) {

        String username = (String) model.get("username");

        Map<String, Object> addedTodo = new HashMap<>();

        addedTodo.put("id", service.retrieveUserTodos(username).size() + 1);
        addedTodo.put("desc", desc);
        addedTodo.put("status", false);

        service.addTodo(username, addedTodo);
        return "redirect:user-todos";
    }


}
