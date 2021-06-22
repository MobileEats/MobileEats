package com.capstone.mobileeats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendorController {

    @GetMapping("/vendors")
    public String vendorsIndex(){
        return "vendors/index";
    }



}
