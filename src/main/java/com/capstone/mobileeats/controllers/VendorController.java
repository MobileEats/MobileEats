package com.capstone.mobileeats.controllers;


import com.capstone.mobileeats.models.Vendor;

import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class VendorController {

    private final VendorRepository vendorDao;

    private VendorRepository vendors;

    public VendorController(VendorRepository vendorDao) {
        this.vendorDao = vendorDao;

    }

    @GetMapping("/vendors")
    public String vendorsIndex(Model model){
        model.addAttribute("vendors", vendorDao.findAll());
        return "vendorIndex";
    }

    @GetMapping("/vendors/create")
    public String vendorCreateProfile(Model model){
        model.addAttribute("vendor", new Vendor());
        return "registerVendor";
    }
    @PostMapping("vendors/create")
    public String createVendor(@ModelAttribute Vendor vendor){
        String hashed = BCrypt.hashpw(vendor.getPassword(), BCrypt.gensalt());
        vendor.setPassword(hashed);
        Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors/profile")
    public String viewProfile(){
        return "vendorProfile";
    }



    @GetMapping("/vendors/profile/{id}")
    public String show(@PathVariable long id, Model model){
        Vendor vendor = vendorDao.getById(id);
        model.addAttribute("vendorId", id);
        model.addAttribute("vendor", vendor);
        return "vendorProfile";
    }

}

