package com.capstone.mobileeats.controllers;



import com.capstone.mobileeats.models.PostTo;

import com.capstone.mobileeats.models.User;

import com.capstone.mobileeats.models.Vendor;

import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class VendorController {

    private final VendorRepository vendorDao;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public VendorController(VendorRepository vendorDao, PasswordEncoder passwordEncoder, EmailService emailService) {

        this.vendorDao = vendorDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;

//        this.categoryDao = categoryDao;
    }

    @GetMapping("/vendor/edit/{id}")
    public String showVendorEditForm(@PathVariable long id, Model model){
        Vendor currentVendor = vendorDao.getById(id);
        model.addAttribute("vendor", vendorDao.getById(currentVendor.getId()));
        return "vendor-profile-edit-page";
    }

    @PostMapping("/vendor/edit/{id}")
    public String editVendorProfile(@ModelAttribute Vendor vendor){
        Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }


    @GetMapping("/vendors") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex( Model model) {
        //        LIST ALL VENDORS
        model.addAttribute("vendors", vendorDao.findAll());

        return "vendorIndex";
    }

    @GetMapping("/vendor") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex(@RequestParam(name = "search") String search, Model model) {
//        SEARCH VENDORS
        String searchQuery = "%" + search + "%";
        model.addAttribute("vendors", vendorDao.searchByTitle(searchQuery)); //searches through title, description, and category

        return "vendorIndex";
    }

    @GetMapping("/vendors/create")
    public String vendorCreateProfile(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "registerVendor";
    }

    @PostMapping("vendors/create")
    public String createVendor(@ModelAttribute Vendor vendor){
        String hashed = BCrypt.hashpw(vendor.getPassword(), BCrypt.gensalt());
        vendor.setPassword(hashed);
        Vendor saveVendor = vendorDao.save(vendor);
        emailService.newVendorCreated(vendor, "New vendor account with MobileEats!", "Thank you for creating an account with MobileEats for " + vendor.getName() + ". \nThe email used for registration is: " +  vendor.getEmail() + "\nThe user name is : " + vendor.getUsername() + " \nIf you find this to be an error please contact us.");
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors/profile/{id}")
    public String show(@PathVariable long id, Model model){
        Vendor vendor = vendorDao.getById(id);
        model.addAttribute("vendorId", id);
        model.addAttribute("vendor", vendor);
        return "vendorProfile";
    }
    @PostMapping(value = "/vendors/profile/{id}")
    public @ResponseBody String sendPost(@RequestBody PostTo postTo, @PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);
        vendor.setLocation(postTo.getLocation());
        vendor.setOpen(postTo.getOpen());
        vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + id;
    }

    //UPDATE
    @GetMapping("/vendors/{id}/edit")
    public String updatePostForm(@PathVariable long id, Model model) {
        model.addAttribute("vendor", vendorDao.getById(id));
        return "vendor-profile-edit-page";
    }

    @PostMapping("/vendors/{id}/edit")
    public String updatePostSubmit(@ModelAttribute Vendor vendor) {
        vendorDao.save(vendor);
        Authentication newAuth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/profile";
    }

}
