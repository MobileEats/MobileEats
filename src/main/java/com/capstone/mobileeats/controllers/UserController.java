package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/profile")
    public String profileView(){
        return "profile";
    }

}