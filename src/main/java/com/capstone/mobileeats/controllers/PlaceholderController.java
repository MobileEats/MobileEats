package com.capstone.mobileeats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceholderController {
    @GetMapping("/test")
    public String test(){
        return "pagetest";
    }
}