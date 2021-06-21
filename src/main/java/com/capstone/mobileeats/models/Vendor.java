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
}
