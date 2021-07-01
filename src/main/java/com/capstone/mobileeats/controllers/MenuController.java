package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.MenuItem;
import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.repositories.MenuItemRepository;
import com.capstone.mobileeats.repositories.MenuRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MenuController {

    private VendorRepository vendors;
    private MenuRepository menus;
    private MenuItemRepository menuItems;

    public MenuController(VendorRepository vendors, MenuRepository menus, MenuItemRepository menuItems){
        this.vendors = vendors;
        this.menus = menus;
        this.menuItems = menuItems;
    }

    @GetMapping("/vendors/{id}/menu")
    public String showMenuScreen(@PathVariable Long id, Model model){
        Vendor vendor = vendors.getById(id);
        List<MenuItem> items = vendor.getMenu().getItems();
        model.addAttribute("vendor", vendor);
        model.addAttribute("items", items);
        return "showMenu";
    }
}
