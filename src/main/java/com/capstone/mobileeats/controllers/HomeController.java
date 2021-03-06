package com.capstone.mobileeats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }

    @GetMapping("/register")
    public String showLoginForm(){
        return "selectRegister";
    }
}