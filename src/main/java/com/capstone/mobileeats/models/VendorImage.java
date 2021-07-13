package com.capstone.mobileeats.models;

import javax.persistence.*;

@Entity
public class VendorImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @Column(name = "image_url")
    private String imageUrl;

    public VendorImage(){}

    public VendorImage(Vendor vendor, String imageUrl) {
        this.vendor = vendor;
        this.imageUrl = imageUrl;
    }

    public VendorImage(long id, Vendor vendor, String imageUrl) {
        this.id = id;
        this.vendor = vendor;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
