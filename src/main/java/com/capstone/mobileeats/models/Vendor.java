package com.capstone.mobileeats.models;


import javax.persistence.*;

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

    @Column(name = "profile_image_url", nullable = true, length = 256)
    private String profileImageUrl;

    @Column(nullable = true, length = 64)
    private String location;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;
}
