package com.capstone.mobileeats.services;

import com.capstone.mobileeats.models.Review;
import com.capstone.mobileeats.models.User;
import com.capstone.mobileeats.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void newUserCreated(User user, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(user.getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void newVendorCreated(Vendor vendor, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(vendor.getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void newReviewCreated(Review review, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
//        msg.setTo(review.getId());// need to do a search by id to get email for user that created the review
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

//    WILL NEED TO CREATE OTHER METHODS TO EMAIL WHEN REVIEW CREATED
}
