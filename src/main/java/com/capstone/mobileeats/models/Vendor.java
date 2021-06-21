package com.capstone.mobileeats.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 4096)
    private String description;

    @Column(name = "phone_number", nullable = true, length = 16)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private int password;

    @Column(name = "profile_image_url", nullable = true, length = 256)
    private String profileImageUrl;

    @Column(nullable = true, length = 64)
    private String location;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    @ManyToMany
    @JoinTable(
            name = "vendor_categories",
            joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    List<VendorCategory> categories;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> followers;

    @ManyToMany
    @JoinTable(
            name = "reviews",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;

    @OneToMany
    @JoinColumn(name = "vendor_id")
    private List<VendorImage> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", referencedColumnName = "vendor_id")
    private Menu menu;

    public Vendor(){}

    public Vendor(String name, String description, String phoneNumber, String email, int password, String profileImageUrl, String location, boolean isOpen, List<VendorCategory> categories, List<User> followers, List<Review> reviews, List<VendorImage> images, Menu menu) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
        this.isOpen = isOpen;
        this.categories = categories;
        this.followers = followers;
        this.reviews = reviews;
        this.images = images;
        this.menu = menu;
    }

    public Vendor(long id, String name, String description, String phoneNumber, String email, int password, String profileImageUrl, String location, boolean isOpen, List<VendorCategory> categories, List<User> followers, List<Review> reviews, List<VendorImage> images, Menu menu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
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
}
