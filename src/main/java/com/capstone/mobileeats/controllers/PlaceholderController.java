package com.capstone.mobileeats.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaceholderController {
    @GetMapping("/test")
    public String test(){
        System.out.println("test");
        return "pagetest";
    }

//    @PostMapping("/test")
//    public String testPost(@ResponseBody){
//        return "pagetest";
//    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String sendPostMessage(@RequestParam("open") String message) {
        return message;
    }
}