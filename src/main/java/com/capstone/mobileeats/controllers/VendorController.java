package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendorController {

    private final VendorRepository vendorDao;

    public VendorController(VendorRepository vendorDao) {
        this.vendorDao = vendorDao;
    }

    @GetMapping("/vendors")
    public String vendorsIndex(Model model){
        model.addAttribute("vendors", vendorDao.findAll());
        return "vendorIndex";
    }
}
