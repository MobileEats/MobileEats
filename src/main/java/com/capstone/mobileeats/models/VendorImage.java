package com.capstone.mobileeats.models;

import javax.persistence.*;

public class VendorImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable=false)
    private Vendor vendor;

    @Column(name = "image_url")
    private String imageUrl;

}
