package com.mohammad.demo.controller;

import com.mohammad.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserLogin(ModelMap model, @RequestParam String username,
                                  @RequestParam String password) {

        if (service.isValidUser(username, password)) {
            model.put("username", username);
            return "redirect:user-todos";
        }
        model.put("errorMessage", "Invalid Username or Password");
        return "login";

    }
}
