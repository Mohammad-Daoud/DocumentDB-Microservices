package com.mohammad.demo.controller;

import com.mohammad.demo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;


@Controller
@SessionAttributes("username")
public class TodoController {
    @Autowired
    TodoService service;


    @RequestMapping(value = "/user-todos", method = RequestMethod.GET)
    public String viewUserTodos(ModelMap model) {
        String username = String.valueOf(model.get("username"));
        model.addAttribute("userTodos", service.retrieveUserTodos(username));
        return "/user-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String viewTodoPage() {
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model,
                          @RequestParam String desc) {
        String username = String.valueOf(model.get("username"));
        Map<String,Object> addedTodo = service.todoCreator(username,desc);
        service.addTodo(username, addedTodo);
        return "redirect:user-todos";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam int id) {
        String username = String.valueOf(model.get("username"));
        service.deleteTodo(username, id);
        return "redirect:user-todos";
    }

    @RequestMapping(value = "/set-done-status", method = RequestMethod.GET)
    public String setDoneStatus(ModelMap model, @RequestParam int id) {
        String username = String.valueOf(model.get("username"));
        service.setDoneStatus(username, id);
        return "redirect:user-todos";
    }
}
