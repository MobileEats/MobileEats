package com.capstone.mobileeats.controllers;

import com.capstone.mobileeats.models.MenuItem;
import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorImage;
import com.capstone.mobileeats.repositories.VendorImageRepository;
import com.capstone.mobileeats.repositories.VendorRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VendorImagesController {
    private VendorImageRepository vendorImages;
    private VendorRepository vendors;

    public VendorImagesController(VendorImageRepository vendorImages, VendorRepository vendors){
        this.vendorImages = vendorImages;
        this.vendors = vendors;
    }

    @GetMapping("/vendors/{id}/images")
    public String showVendorImages(Model model, @PathVariable long id){
        //can only view the images page if it's your account
        try {
            Vendor user = (Vendor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getId() == id) {
                Vendor vendor = vendors.getById(id);
                List<VendorImage> images = vendor.getImages();
                model.addAttribute("vendor", vendor);
                model.addAttribute("images", images);
                return "showVendorImages";
            }
            else {
                return "redirect:/vendors";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/vendors";
        }
    }

    @GetMapping("/vendors/{vendorId}/images/{imageId}/delete")
    public String deleteMenuItem(@PathVariable Long vendorId, @PathVariable Long imageId, Model model) {
        VendorImage image = vendorImages.getById(imageId);
        vendorImages.delete(image);
        return "redirect:/vendors/" + vendorId + "/images";
    }

    @GetMapping("/vendors/{id}/images/create")
    public String showCreateImageForm(Model model, @PathVariable long id) {
        model.addAttribute("vendor", vendors.getById(id));
        model.addAttribute("image", new VendorImage());
        return "addVendorImage";
    }

    @PostMapping("/vendors/{id}/images/create")
    public String createVendorImage(@PathVariable long id, @ModelAttribute VendorImage image){
        Vendor vendor = vendors.getById(id);
        image.setVendor(vendor);
        vendorImages.save(image);
        return "redirect:/vendors/" + id + "/images";
    }
}
