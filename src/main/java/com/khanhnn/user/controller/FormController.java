package com.khanhnn.user.controller;

import com.khanhnn.user.model.User;
import com.khanhnn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ModelAndView index (Pageable pageable){
        Page<User> users = userService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/user/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create", "user", new User());
        return modelAndView;
    }

    @PostMapping("/user/create")
    public String add(@Validated @ModelAttribute("User") User user, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "create";
        }else {
            return "result";
        }
    }
}
