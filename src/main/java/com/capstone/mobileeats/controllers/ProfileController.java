package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.PostTo;
import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    private UserRepository users;
    private VendorRepository vendors;//same as vendorsdao


    public ProfileController(UserRepository users, VendorRepository vendors){
        this.users = users;
        this.vendors = vendors;
    }

    @GetMapping("/profile")
    public String userOrVendor(Model model){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
            return "userOwnedProfile";
        }catch(ClassCastException e){
            Vendor vendor = (Vendor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("vendor", vendor);
            return "vendorOwnedProfile";
        }
    }
    @PostMapping(value = "/profile")
    public @ResponseBody
    String sendPost(@RequestBody PostTo postTo) {
        Vendor updateVendor = vendors.getById(postTo.getId());
        updateVendor.setLocation(postTo.getLocation());
        updateVendor.setOpen(postTo.getOpen());
        vendors.save(updateVendor);
        return "vendorProfile";
    }


}
