package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Menu;
import com.capstone.mobileeats.models.MenuItem;
import com.capstone.mobileeats.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
       @Query("SELECT image_url FROM MenuItem where menu.id = :id")
    List<String> findAllByMenuId(@Param("id") long id);

}

