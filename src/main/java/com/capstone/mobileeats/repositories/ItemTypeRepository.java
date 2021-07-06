package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {
    ItemType findByName(String name);
}
