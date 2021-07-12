package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Vendor;
import com.capstone.mobileeats.models.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorCategoryRepository extends JpaRepository<VendorCategory, Long> {
    public VendorCategory findByName(String name);
}
