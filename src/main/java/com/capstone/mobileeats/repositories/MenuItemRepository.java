package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{

}
