package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByEmail(String email);

    //    SEARCH BY TITLE/ DESCRIPTION
    @Query("FROM Vendor WHERE name LIKE %:term% OR description LIKE %:term%" +
            //    SEARCH BY CATEGORY
//            "OR id IN(SELECT vendor_id FROM vendors_categories WHERE category_id IN(SELECT id FROM VendorCategory WHERE name LIKE %:term%))" +
            "")
    List<Vendor> searchByTitle(@Param("term") String term);

//    @Query("FROM Vendor v JOIN VendorCategory c WHERE c.name LIKE %:category%")
//    List<Vendor> findAllByCategories(@Param("category") String category);
}
