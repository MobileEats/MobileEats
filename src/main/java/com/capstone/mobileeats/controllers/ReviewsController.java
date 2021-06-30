package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Review;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.ReviewRepository;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewsController {
    private final UserRepository users;
    private final VendorRepository vendors;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ReviewRepository reviews;

    public ReviewsController(ReviewRepository reviews, UserRepository users, VendorRepository vendors, PasswordEncoder passwordEncoder, EmailService emailService, ReviewRepository reviews1) {
        this.users = users;
        this.vendors = vendors;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.reviews = reviews1;
    }

    @GetMapping("/review/create/{id}")
    public String showReviewForm(@PathVariable long id, Model model){
        model.addAttribute("review", new Review());
        return "reviews-page";
    }

    @PostMapping("/review/create/{id}")
    public String leaveReview(@ModelAttribute Review review){
        Review saveReview = reviews.save(review);
        return "redirect:/vendors/profile/" + review.getVendor().getId();
    }

}
