package com.capstone.mobileeats.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "image_url", nullable = true, length = 255)
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "menu")
    private List<MenuItem> items;

    public Menu(){}

    public Menu(long id, String name, String imageUrl, Vendor vendor, List<MenuItem> items) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
        this.items = items;
    }

    public Menu(String name, String imageUrl, Vendor vendor, List<MenuItem> items) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
        this.items = items;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
