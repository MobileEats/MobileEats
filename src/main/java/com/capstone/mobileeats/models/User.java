package com.capstone.mobileeats.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true, length = 100)
    private String first_name;

    @Column(nullable = true, length = 100)
    private String last_name;

    @Column(nullable = true, length = 100)
    private String email;

    @Column(length = 255)
    private String image_url;

    @Column(nullable = true, length = 100)
    private String username;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true, length = 255)
    private String location;

    @Column
    private boolean is_admin;

    @ManyToMany
        @JoinTable(
                name = "followers",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "vendor_id")
        )
    private List<Vendor> following = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Review> reviews;

    public User() {}

    public User(User copy) {
        id = copy.id;
        first_name = copy.first_name;
        last_name = copy.last_name;
        email = copy.email;
        image_url = copy.image_url;
        username = copy.username;
        password = copy.password;
        location = copy.location;
        is_admin = copy.is_admin;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(long id, String first_name, String last_name, String email, String image_url, String username, String password, String location, boolean is_admin) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.image_url = image_url;
        this.username = username;
        this.password = password;
        this.location = location;
        this.is_admin = is_admin;
    }

    public List<Vendor> getFollowing() {
        return following;
    }

    public void setFollowing(List<Vendor> following) { this.following = following; }

    public List<Review> getReviews() {
        return reviews;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}


