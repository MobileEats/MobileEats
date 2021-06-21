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
}
