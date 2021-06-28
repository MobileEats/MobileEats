package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

@Controller
public class UserController {

    private final UserRepository users;
    private final VendorRepository vendors;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, EmailService emailService, VendorRepository vendors) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.vendors = vendors;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    //reset password
    @GetMapping("/resetPassword")
    public String showResetPasswordForm(){ return "resetPassword"; }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email){
        String newPasswordPlain = genPassword();
        String newPasswordHashed = BCrypt.hashpw(newPasswordPlain, BCrypt.gensalt());
        System.out.println(email);
        User user = users.findByEmail(email);
        System.out.println(user.getEmail());
        if (Objects.isNull(user)){
            Vendor vendor = vendors.findByEmail(email);
            if (Objects.isNull(vendor)){
                return "resetPasswordError";
            }
            vendor.setPassword(newPasswordHashed);
            vendors.save(vendor);
            emailService.passwordReset(email, "MobileEats Password Reset", "Here is your new password for MobileEats: " + newPasswordPlain);
        }
        user.setPassword(newPasswordHashed);
        users.save(user);
        emailService.passwordReset(email, "MobileEats Password Reset", "Here is your new password for MobileEats: " + newPasswordPlain);
        return "resetPasswordConfirm";
    }

    public static String genPassword() {
      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
      String specialCharacters = "!@#$";
      String numbers = "1234567890";
      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
      Random random = new Random();
      String password = "";

      for(int i = 0; i < 32; i++) {
         password += combinedChars.charAt(random.nextInt(combinedChars.length()));
      }
      return password;
    }

    @GetMapping("/users/create")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("users/create")
    public String createVendor(@ModelAttribute User user){
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        User saveUser = users.save(user);
        emailService.newUserCreated(user, "New user account with MobileEats!", "Thank you for creating a user account with MobileEats. \nThe email used for registration is: " +  user.getEmail() + "\nThe user name is : " + user.getUsername() + " \nIf you find this to be an error please contact us.");
        return "redirect:/users/profile/" + saveUser.getId();
    }

    @GetMapping(path = "/user/{id}/profile")
    public String postId(@PathVariable long id, Model model) {
        model.addAttribute("user", users.getById(id));
        return "ownedUserProfile";
    }


}