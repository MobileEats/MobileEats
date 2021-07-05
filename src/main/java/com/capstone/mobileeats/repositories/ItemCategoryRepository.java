package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    ItemCategory findByName(String name);
}
