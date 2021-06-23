package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.daos.FoodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceholderController {

    private final FoodRepository foodDao;

    public PlaceholderController(FoodRepository foodDao){
        this.foodDao = foodDao;
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("vendors" , foodDao.findAll());
//        model.addAttribute("burgerVendors", foodDao.findByCategories("burgers"));
        model.addAttribute("searchVendors", foodDao.findByCategoriesLike("burg"));
        return "pagetest";
    }
}