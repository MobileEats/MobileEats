package com.capstone.mobileeats.controllers;


import com.capstone.mobileeats.models.Vendor;

import com.capstone.mobileeats.models.VendorCategory;
import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class VendorController {

    private final VendorRepository vendorDao;

    private VendorRepository vendors;

    public VendorController(VendorRepository vendorDao) {
        this.vendorDao = vendorDao;

    }

    @GetMapping("/vendors") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex(@RequestParam(name = "search") String search,
//                               @RequestParam(name = "category") String category,
                               Model model) {
//        LIST ALL VENDORS
        model.addAttribute("vendors", vendorDao.findAll());
        String searchQuery = "%" + search + "%";
        model.addAttribute("searchVendor", vendorDao.searchByTitle(searchQuery));

//        List<Vendor> vendors = vendorDao.findAll(); //all vendors
//        List<Vendor> newVendors = new ArrayList<>();
//
//
//        for(int i = 0; i < vendors.size(); i++){
//            Vendor vendor = vendors.get(i);
//            if(vendor.getCategories().contains(category)){
//                newVendors.add(vendor);
//            }
//        }


        return "vendorIndex";
    }

    @GetMapping("/vendors/create")
    public String vendorCreateProfile(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "vendorInput";
    }

    @PostMapping("vendors/create")
    public String createVendor(@ModelAttribute Vendor vendor) {
//        Vendor newVendor = new Vendor(name, description,  phoneNumber,  profileImageUrl, location);
        Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors/profile")
    public String viewProfile() {
        return "vendorProfile";
    }

    @GetMapping("/vendors/profile/{id}")
    public String show(@PathVariable long id, Model model) {
        Vendor vendor = vendorDao.getById(id);
        model.addAttribute("vendorId", id);
        model.addAttribute("vendor", vendor);
        return "vendorProfile";
    }

}

