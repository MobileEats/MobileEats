package com.capstone.mobileeats.models;

import javax.persistence.*;

@Entity
@Table(name = "menu_items_types")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 64)
    private String name;

    public ItemType(){}

    public ItemType(String name) {
        this.name = name;
    }

    public ItemType(long id, String name) {
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
