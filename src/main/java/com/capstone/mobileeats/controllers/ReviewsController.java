package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.Review;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.repositories.ReviewRepository;
import com.capstone.mobileeats.repositories.UserRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import com.capstone.mobileeats.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/{userId}/reviews/{vendorId}")
    public String showReviewForm(@PathVariable long userId, @PathVariable long vendorId, Model reviewModel, Model vendorModel, Model userModel){
        userModel.addAttribute("user", users.getById(userId));
        reviewModel.addAttribute("review", new Review());
        vendorModel.addAttribute("vendor", vendors.getById(vendorId));
        return "reviews-page";
    }

    @PostMapping("/{userId}/reviews/{vendorId}")
    public String leaveReview(@PathVariable long userId, @PathVariable long vendorId, @ModelAttribute Review review){
        review.setVendor(vendors.getById(vendorId));
        review.setOwner(users.getById(userId));
        reviews.save(review);
        emailService.newReviewUser(review, "You submitted a review!", "Thank you for submitting a review. \nYou submitted a review for : " +  review.getVendor().getName() + "\nYou submitted a rating of : " + review.getRating() + " \nAnd this is the comment you entered: " + review.getBody() + "\n\nIf you believe this is incorrect please let us know.");
        emailService.newReviewVendor(review, "Review was submitted of your business!", "Below is a copy of what was submitted. \nYou received a rating of : " + review.getRating() + " \nAnd this is the comment that was entered: " + review.getBody() + "\n\nIf you have any question please contact MobileEats.");
        return "index";
    }

}
