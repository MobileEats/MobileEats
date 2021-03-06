
package com.capstone.mobileeats.models;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name="vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true, length = 4096)
    private String description;

    @Column(name = "phone_number", nullable = true, length = 16)
    private String phoneNumber;

    @Column(nullable = true, unique = true, length = 255)
    private String email;


    @Column(nullable = true, length = 255)
    private String password;

    @Column(length = 255)
    private String image_url;

    @Column(nullable = true, length = 255)
    private String location;

    @Column(name = "is_open", nullable = true)
    private boolean isOpen = false;

    @ManyToMany
    @JoinTable(
            name = "vendors_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "vendor_id")
    )
    private List<VendorCategory> categories;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> followers = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "vendor")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "vendor")
    private List<VendorImage> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "vendor_id", nullable = true)//
    private Menu menu;

    public Vendor(){}

    public Vendor(Vendor copy){
        id = copy.id;
        description = copy.description;
        phoneNumber = copy.phoneNumber;
        email = copy.email;
        password = copy.password;
        image_url = copy.image_url;
        location = copy.location;
        isOpen = copy.isOpen;
        categories = copy.categories;
        followers = copy.followers;
        reviews = copy.reviews;
        images = copy.images;
        menu = copy.menu;
    }


    public Vendor(String name, String description, String phoneNumber, String image_url, String location) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.image_url = image_url;
        this.location = location;
    }


    public Vendor(String name, String description, String phoneNumber, String email, String password, String image_url, String location, boolean isOpen, List<VendorCategory> categories, List<User> followers, List<Review> reviews, List<VendorImage> images, Menu menu) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.image_url = image_url;
        this.location = location;
        this.isOpen = isOpen;
        this.categories = categories;
        this.followers = followers;
        this.reviews = reviews;
        this.images = images;
        this.menu = menu;
    }

    public Vendor(long id, String name, String description, String phoneNumber, String email, String password, String image_url, String location, boolean isOpen, List<VendorCategory> categories, List<User> followers, List<Review> reviews, List<VendorImage> images, Menu menu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.image_url = image_url;
        this.location = location;
        this.isOpen = isOpen;
        this.categories = categories;
        this.followers = followers;
        this.reviews = reviews;
        this.images = images;
        this.menu = menu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<VendorCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<VendorCategory> categories) {
        this.categories = categories;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<VendorImage> getImages() {
        return images;
    }

    public void setImages(List<VendorImage> images) {
        this.images = images;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getUsername(){ return ""; }
}

