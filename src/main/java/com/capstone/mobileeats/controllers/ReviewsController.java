package com.capstone.mobileeats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewsController {
    @GetMapping("/profile")
    public String viewProfile(){
        return "vendorProfile";
    }
}
