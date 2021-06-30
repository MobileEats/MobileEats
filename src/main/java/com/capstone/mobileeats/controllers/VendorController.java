package com.capstone.mobileeats.controllers;


import com.capstone.mobileeats.models.PostTo;

import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.models.Vendor;

import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.lang.model.element.Element;
import javax.script.SimpleScriptContext;
import javax.swing.text.Document;


@Controller
public class VendorController {

    private final UserRepository userDao;
    private final VendorRepository vendorDao;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public VendorController(UserRepository userDao, VendorRepository vendorDao, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;

        this.vendorDao = vendorDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/vendor/edit/{id}")
    public String showVendorEditForm(@PathVariable long id, Model model) {
        Vendor currentVendor = vendorDao.getById(id);
        model.addAttribute("vendor", vendorDao.getById(currentVendor.getId()));
        return "vendor-profile-edit-page";
    }

    @PostMapping("/vendor/edit/{id}")
    public String editVendorProfile(@ModelAttribute Vendor vendor) {
        Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex(Model model) {
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
    public String createVendor(@ModelAttribute Vendor vendor) {
        String hashed = BCrypt.hashpw(vendor.getPassword(), BCrypt.gensalt());
        vendor.setPassword(hashed);
        Vendor saveVendor = vendorDao.save(vendor);

        emailService.newVendorCreated(vendor, "New vendor account with MobileEats!", "Thank you for creating an account with MobileEats for " + vendor.getName() + ". \nThe email used for registration is: " + vendor.getEmail() + "\nThe user name is : " + vendor.getUsername() + " \nIf you find this to be an error please contact us.");
        return "redirect:/profile";
//        return "redirect:/vendors/profile/" + saveVendor.getId();
    }


    @GetMapping("/vendors/profile/{id}")
    public String show(@PathVariable long id, Model model) {

        Vendor vendor = vendorDao.getById(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("vendorId", id);
        model.addAttribute("vendor", vendor);
        model.addAttribute("user", currentUser);

        User test = userDao.getById(1L);

        if (vendor.getFollowers().contains(test)) {
            String following = "Following";
            model.addAttribute("following", following);
        } else {
            String follow = "+ Follow";
            model.addAttribute("following", follow);
        }
//        model.addAttribute("location", vendor.getLocation());
//        ObjectMapper objectMapper = new ObjectMapper();
//        result.addObject("location", objectMapper.writeValueAsString(location));
        return "vendorProfile";
    }

    @PostMapping(value = "/vendors/profile/{id}")
    public @ResponseBody
    String sendPost(@RequestBody PostTo postTo, @PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);
        vendor.setLocation(postTo.getLocation());
        vendor.setOpen(postTo.getOpen());

        vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + id;
    }

    @PostMapping("/vendors/profile/{id}/follow")
    public String follow(@PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);

        User test = userDao.getById(1L);

        if (vendor.getFollowers().contains(test)) {
            vendor.getFollowers().remove(test);
        } else {
            vendor.getFollowers().add(test);
        }

        vendorDao.save(vendor);

        return "redirect:/vendors/profile/" + id;
    }
}
