package com.capstone.mobileeats.controllers;


import com.capstone.mobileeats.models.*;

import com.capstone.mobileeats.repositories.ReviewRepository;
import com.capstone.mobileeats.repositories.MenuRepository;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class VendorController {

    private final UserRepository userDao;
    private final VendorRepository vendorDao;
    private final ReviewRepository reviewDao;
    private final MenuRepository menuDao;

    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public VendorController(MenuRepository menuDao, UserRepository userDao, VendorRepository vendorDao,ReviewRepository reviewDao, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;
        this.menuDao = menuDao;

        this.vendorDao = vendorDao;
        this.reviewDao = reviewDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/vendor/edit/{id}")
    public String showVendorEditForm(@PathVariable long id, Model model) {
        Vendor currentVendor = vendorDao.getById(id);
        model.addAttribute("vendor", vendorDao.getById(currentVendor.getId()));
        return "editVendorProfilePage";
    }

    @PostMapping("/vendor/edit/{id}")
    public String editVendorProfile(@ModelAttribute Vendor vendor) {
        Vendor saveVendor = vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + saveVendor.getId();
    }

    @GetMapping("/vendors") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex(Model model) {
        //        LIST ALL VENDORS
        List<Vendor> vendors = vendorDao.findAll();
        return getString(model, vendors);
    }

    @GetMapping("/vendor") //tried creating separate post mapping for the search queries but returns whitelabel error
    public String vendorsIndex(@RequestParam(name = "search") String search, Model model) {
//        SEARCH VENDORS

        String searchQuery = "%" + search + "%";
        List<Vendor> searchedVendors = vendorDao.searchByTitle(searchQuery);

        return getString(model, searchedVendors);
    }

//    this is just a method containing my AVERAGE RATING function so I don't have to repeat it in the vendor and vendors mappings
    private String getString(Model model, List<Vendor> vendors) {
        model.addAttribute("vendors", vendors); //searches through title, description, and category

        List<Double> averages = new ArrayList<>();

        for(int i = 0; i < vendors.size(); i++){
            double addRatings = 0;
            List<Review> reviews = vendors.get(i).getReviews();
            for(int j = 0; j < reviews.size(); j++){
                addRatings += reviews.get(j).getRating();
            }
            double averageRating = addRatings / reviews.size();

            averages.add((double) Math.round(averageRating * 100)/100);
        }
        model.addAttribute("rating", averages);

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
        if (vendor.getImage_url().isBlank()){
            vendor.setImage_url("/images/user-solid.svg");
        }
        vendorDao.save(vendor);
        Menu menu = new Menu("Menu", "", vendor, new ArrayList<>());
        menuDao.save(menu);
        vendor.setMenu(menu);
        vendorDao.save(vendor);

        emailService.newVendorCreated(vendor, "New vendor account with MobileEats!", "Thank you for creating an account with MobileEats for " + vendor.getName() + ". \nThe email used for registration is: " + vendor.getEmail() + "\nThe user name is : " + vendor.getUsername() + " \nIf you find this to be an error please contact us.");
        return "redirect:/profile";
//        return "redirect:/vendors/profile/" + saveVendor.getId();
    }


    @GetMapping("/vendors/profile/{id}")
    public String show(@PathVariable long id, Model model) {

        try{
            Vendor vendor = vendorDao.getById(id);
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //checks if user is logged in
            User user = userDao.getById(currentUser.getId());

            model.addAttribute("user", user);
            model.addAttribute("vendorId", id);
            model.addAttribute("vendor", vendor);

            if (vendor.getFollowers().contains(user)) {
                String following = "Following";
                model.addAttribute("following", following);
            } else {
                String follow = "+ Follow";
                model.addAttribute("following", follow);
            }

            System.out.println("user " + user.getUsername() + " viewing: " + vendor.getName());

        } catch (ClassCastException e){ //if a user isn't logged in, it will check to see if they are a vendor or a guest
            try{
                Vendor currentVendor = (Vendor) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //checks if vendor is logged in
                User user = userDao.getById(currentVendor.getId());
                Vendor vendor = vendorDao.getById(id);

                model.addAttribute("loggedVendor", user);
                model.addAttribute("vendorId", id);
                model.addAttribute("vendor", vendor);

                Vendor meAsAVendor = vendorDao.getById(currentVendor.getId());
                System.out.println("vendor " + meAsAVendor.getName() + " viewing: " + vendor.getName());

            } catch (ClassCastException f){ //catches exception when no vendor or user is logged in (guest)
                    Vendor vendor = vendorDao.getById(id);
                    model.addAttribute("vendorId", id); //still needs vendor info to display the correct page
                    model.addAttribute("vendor", vendor);

                    System.out.println("user guest viewing: " + vendor.getName());

            }
            model.addAttribute("user", null); //workaround for review link in vendorProfile... hard codes user to null if it does not detect a logged user, which is then checked in the html using th:switch case
        }
        return "vendorProfile";
////        model.addAttribute("location", vendor.getLocation());
////        ObjectMapper objectMapper = new ObjectMapper();
////        result.addObject("location", objectMapper.writeValueAsString(location));
    }
//************ do we need this **********************
    @PostMapping(value = "/vendors/profile/{id}")
    public @ResponseBody
    String sendPost(@RequestBody PostTo postTo, @PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);
        vendor.setLocation(postTo.getLocation());
        vendor.setOpen(postTo.getOpen());

        vendorDao.save(vendor);
        return "redirect:/vendors/profile/" + id;
    }

    //UPDATE
    @GetMapping("/vendors/{id}/edit")
    public String updatePostForm(@PathVariable long id, Model model) {
        try{
            Vendor currentVendor = (Vendor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (id == currentVendor.getId()) {
                model.addAttribute("vendor", vendorDao.getById(id));
                return "editVendorProfilePage";
            }
            return "redirect:/vendors";
        }
        catch(ClassCastException e){
            return "redirect:/vendors";
        }

    }

    @PostMapping("/vendors/{id}/edit")
    public String updatePostSubmit(@ModelAttribute Vendor vendor) {
        vendorDao.save(vendor);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/profile";
    }


    @PostMapping("/vendors/profile/{id}/follow")
    public String follow(@PathVariable Long id) {
        try {
            Vendor vendor = vendorDao.getById(id);

            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userDao.getById(currentUser.getId());

            if (vendor.getFollowers().contains(user)) {
                vendor.getFollowers().remove(user);
            } else {
                vendor.getFollowers().add(user);
            }

            vendorDao.save(vendor);
            return "redirect:/vendors/profile/" + id;

        } catch (ClassCastException e){
            return "redirect:/vendors/profile/" + id;
        }
    }

    //CONTACT
    @GetMapping("/vendors/contact")
    public String contact(){
        return "contactUsPage";
    }

    @GetMapping("/vendors/profile/{id}/reviews")
    public String reviews(@PathVariable Long id, Model model){
        Vendor vendor = vendorDao.getById(id);
        List<Review> vendorReviews = vendor.getReviews();

        model.addAttribute("reviews", vendorReviews);
        model.addAttribute("vendor", vendor);

        System.out.println("vendorReviews = " + vendorReviews);
        return "vendorReviews";
    }
}
