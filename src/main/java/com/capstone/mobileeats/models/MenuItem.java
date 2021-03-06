package com.capstone.mobileeats.models;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true, length = 255)
    private String name;

    @Column(nullable = true, length = 1000)
    private String description;

    @Column(length = 255)
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @ManyToOne
    @JoinColumn (name = "type_id")
    private ItemType itemType;

    @ManyToMany
    @JoinTable(
            name = "menu_items_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<ItemCategory> categories;

    public MenuItem(){}

    public MenuItem(MenuItem copy){
        id = copy.id;
        name = copy.name;
        description = copy.description;
        image_url = copy.image_url;
        menu = copy.menu;
        itemType = copy.itemType;
    }

    public MenuItem(long id, String name, String description, String image_url, Menu menu, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.menu = menu;
        this.itemType = itemType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public List<ItemCategory> getCategories(){ return categories; }

    public void setCategories(List<ItemCategory> categories){ this.categories = categories; }
}
