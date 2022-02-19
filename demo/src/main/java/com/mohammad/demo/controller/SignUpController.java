package com.mohammad.demo.controller;

import com.mohammad.demo.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    SignupService service;
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String handleUserSignUp(ModelMap model,
                                   @RequestParam String regUsername,
                                  @RequestParam String regPassword) {
        if (service.isUserExist(regUsername)) {
            model.put("regErrorMessage", "username is exist, choose different one ");
            return "signup";
        }
        service.registerNewUser(regUsername,regPassword);
        return "redirect:login";

    }
}
