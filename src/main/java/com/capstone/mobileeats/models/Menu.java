package com.capstone.mobileeats.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(name = "image_url", nullable = true, length = 256)
    private String imageUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany
    @JoinColumn(name = "menu_id")
    private List<MenuItem> items;

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
