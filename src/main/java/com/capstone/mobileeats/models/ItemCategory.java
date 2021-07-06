package com.capstone.mobileeats.models;

import java.awt.*;
import javax.persistence.*;

@Entity
@Table(name = "menu_item_categories")
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true, length = 255)
    private String name;

    public ItemCategory(){}

    public ItemCategory(ItemCategory copy){
        id = copy.id;
        name = copy.name;
    }

    public ItemCategory(long id, String name) {
        this.id = id;
        this.name = name;
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
}
