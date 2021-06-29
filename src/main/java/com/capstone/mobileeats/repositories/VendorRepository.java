package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByEmail(String email);


            //SEARCH BY TITLE
    @Query("FROM Vendor WHERE name LIKE %:search% " +
            //SEARCH BY DESCRIPTION
            "OR description LIKE %:search%" +
            //SEARCH BY CATEGORY
            " OR id = ANY (SELECT id FROM VendorCategory WHERE name LIKE %:search%)" +
            "")
    List<Vendor> searchByTitle(@Param("search") String search);
}
