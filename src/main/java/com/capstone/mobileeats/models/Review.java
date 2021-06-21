package com.capstone.mobileeats.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int rating;

    @Column(length = 4096)
    private String body;

    @OneToOne
    private User owner;

    @OneToOne
    private Vendor vendor;

    public Review(){}

    public Review(Review copy) {
        id = copy.id;
        rating = copy.rating;
        body = copy.body;
        owner = copy.owner;
        vendor = copy.vendor;
    }

    public Review(long id, int rating, String body, User owner, Vendor vendor) {
        this.id = id;
        this.rating = rating;
        this.body = body;
        this.owner = owner;
        this.vendor = vendor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
