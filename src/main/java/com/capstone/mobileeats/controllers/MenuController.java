package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.*;
import com.capstone.mobileeats.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    private VendorRepository vendors;
    private MenuRepository menus;
    private MenuItemRepository menuItems;
    private ItemTypeRepository itemTypes;
    private ItemCategoryRepository itemCategories;

    public MenuController(VendorRepository vendors, MenuRepository menus, MenuItemRepository menuItems, ItemTypeRepository itemTypes, ItemCategoryRepository itemCategories){
        this.vendors = vendors;
        this.menus = menus;
        this.menuItems = menuItems;
        this.itemTypes = itemTypes;
        this.itemCategories = itemCategories;
    }

    @GetMapping("/vendors/{id}/menu")
    public String showMenuScreen(@PathVariable Long id, Model model){
        Vendor vendor = vendors.getById(id);
        List<MenuItem> items = vendor.getMenu().getItems();
        model.addAttribute("vendor", vendor);
        model.addAttribute("items", items);
        return "showMenu";
    }

    @GetMapping("/vendors/{vendorId}/menu/{menuItemId}/delete")
    public String deleteMenuItem(@PathVariable Long vendorId, @PathVariable Long menuItemId, Model model){
        MenuItem item = menuItems.getById(menuItemId);
        menuItems.delete(item);
        System.out.println("HELLO!");
        return "redirect:/vendors/" + vendorId + "/menu";
    }

    @GetMapping("/vendors/{id}/menu/create")
    public String showCreateItemForm(Model model, @PathVariable long id){
        model.addAttribute("vendor", vendors.getById(id));
        model.addAttribute("types", itemTypes.findAll());
        model.addAttribute("categories", itemCategories.findAll());
        return "addMenuItem";
    }
    @PostMapping("/vendors/{id}/menu/create")
    public String createItem(@RequestParam(name = "type") String typeName, @RequestParam(name = "categories") String categoriesString, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description, @RequestParam(name = "image_url") String imageUrl, @PathVariable long id){
        MenuItem item = new MenuItem();
        System.out.println(categoriesString);
        ItemType type = itemTypes.findByName(typeName);
        List<ItemCategory> categories = new ArrayList<>();
        Menu menu = vendors.getById(id).getMenu();
        String[] categoriesNames = convertStringToList(categoriesString);
        for (String categoryName : categoriesNames){
            categories.add(itemCategories.findByName(categoryName));
        }
        item.setItemType(type);
        item.setCategories(categories);
        item.setName(name);
        item.setDescription(description);
        item.setImage_url(imageUrl);
        item.setMenu(menu);
        menuItems.save(item);
        return "redirect:/vendors/" + id + "/menu";
    }

    public String[] convertStringToList(String str){
        //remove the brackets
        String newStr = str.replaceAll("[|]", "");
        return newStr.split(",");
    }
}
