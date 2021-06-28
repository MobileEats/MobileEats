package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private UserRepository users;
    private VendorRepository vendors;

    public ProfileController(UserRepository users, VendorRepository vendors){
        this.users = users;
        this.vendors = vendors;
    }

    @GetMapping("/profile")
    public String userOrVendor(Model model){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/user/" + user.getId() + "/profile";
        }catch(ClassCastException e){
            Vendor vendor = (Vendor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/vendors/profile/" + vendor.getId();
        }
    }

}
