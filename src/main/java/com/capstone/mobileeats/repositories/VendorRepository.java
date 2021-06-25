package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByEmail(String email);

    @Query("from Vendor as v where v.name like %:term% or v.description like %:term%")
    List<Vendor> searchByTitle(@Param("term") String term);
}
