package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/users/create")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("users/create")
    public String createVendor(@ModelAttribute User user){
        User saveUser = users.save(user);
        return "redirect:/users/profile/" + saveUser.getId();
    }

    @GetMapping(path = "/user/{id}/profile")
    public String postId(@PathVariable long id, Model model) {
        model.addAttribute("user", users.getById(id));
        return "user-profile-page";
    }


}