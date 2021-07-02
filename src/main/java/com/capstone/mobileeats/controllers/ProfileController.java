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

import java.util.List;

@Controller
public class ProfileController {
    private UserRepository userDao;
    private VendorRepository vendorDao;


    public ProfileController(UserRepository userDao, VendorRepository vendorDao){
        this.userDao = userDao;
        this.vendorDao = vendorDao;
    }

    @GetMapping("/profile")
    public String userOrVendor(Model model){
        try{
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", currentUser);

            User user = userDao.getById(currentUser.getId());
            System.out.println("user = " + user);

            List<Vendor> following = currentUser.getFollowing();
            List<Vendor> vendors = vendorDao.findAll();

            for(int i = 0; i < vendors.size(); i++){
//                System.out.println("test " + i + " = " + vendors.get(i));
                Vendor vendor = vendors.get(i);

                if(vendor.getFollowers().contains(user) & !following.contains(vendor)){
                    following.add(vendor);
                }
                System.out.println("vendor followers: " + vendor.getFollowers());
            }
//            ^^error: repeats the list every time the page refreshes

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
        Vendor updateVendor = vendorDao.getById(postTo.getId());
        updateVendor.setLocation(postTo.getLocation());
        updateVendor.setOpen(postTo.getOpen());
        vendorDao.save(updateVendor);
        return "vendorProfile";
    }


}
