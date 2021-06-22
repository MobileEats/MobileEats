package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VendorController {

    private final VendorRepository vendorDao;
    private VendorRepository vendors;

    public VendorController(VendorRepository vendorsRepository) {

        this.vendorDao = vendorsRepository;
    }

    @GetMapping("/vendors")
    public String vendorsIndex(Model model){
        model.addAttribute("vendors", vendorDao.findAll());
        return "vendorIndex";
    }
    @GetMapping("/vendors/create")
    public String vendorCreateProfile(Model model){
        model.addAttribute("vendor", new Vendor());
        return "vendorInput";
    }
    @PostMapping("vendors/create")
    public String createVendor(@ModelAttribute Vendor vendor){
//        Vendor newVendor = new Vendor(name, description,  phoneNumber,  profileImageUrl, location);
    Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors/profile")
    public String viewProfile(){
        return "vendorProfile";
    }
}
