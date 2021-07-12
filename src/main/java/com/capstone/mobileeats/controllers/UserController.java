package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        User user = users.findByEmail(email);
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
        if (user.getImage_url().isBlank()){
            user.setImage_url("/images/user-solid.svg");
        }
        user.setPassword(hashed);
        User saveUser = users.save(user);

        emailService.newUserCreated(user, "New user account with MobileEats!", "Thank you for creating a user account with MobileEats. \nThe email used for registration is: " +  user.getEmail() + "\nThe user name is : " + user.getUsername() + " \nIf you find this to be an error please contact us.");
        return "redirect:/profile";
//        return "redirect:/users/profile/" + saveUser.getId();

    }
//*******is thi needed ********
    @GetMapping(path = "/user/profile/{id}")
    public String postId(@PathVariable long id, Model model) {
        model.addAttribute("user", users.getById(id));
        return "userProfile";
    }

    //UPDATE
    @GetMapping("/users/{id}/edit")
    public String updatePostForm(@PathVariable long id, Model model) {
        try{
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(id == currentUser.getId()){
                User user = users.getById(currentUser.getId());
                model.addAttribute("user", user);
                List<Vendor> following = user.getFollowing();
                model.addAttribute("following", following);
                return "editUserProfilePage";
            }
            return "redirect:/vendors";
        }
        catch(ClassCastException e){
            return "redirect:/vendors";
        }
    }

    @PostMapping("/users/{id}/edit")
    public String updatePostSubmit(@ModelAttribute User user, Model model) {
        users.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/profile";
    }

    //edit password
    @GetMapping("/users/{id}/editPassword")
    public String showEditPassword(@PathVariable long id){
        return "editPassword";
    }

    @PostMapping("/users/{id}/editPassword")
    public String editPassword(@PathVariable long id, @RequestParam String oldPassword, @RequestParam String newPassword){
        User user = users.getById(id);
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        if(Objects.isNull(user)){
            return "redirect:/editPassword";
        }
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("oldPassword = " + oldPassword);
        if(BCrypt.checkpw(oldPassword, user.getPassword())){
            user.setPassword(hashedPassword);
            users.save(user);
        }
        return "passwordChangeConfirm";
    }

}