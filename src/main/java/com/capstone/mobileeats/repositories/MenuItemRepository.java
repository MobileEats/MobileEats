package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Menu;
import com.capstone.mobileeats.models.MenuItem;
import com.capstone.mobileeats.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
    List<MenuItem> findAllByIdLessThan(long id);
//    List<MenuItem> findAllByMenu(long id);
//    List<MenuItem> findMenuItemsByMenu(long id);
//    List<MenuItem> findAllByMenuIs(long id);

//    List<MenuItem> findMenuItemsBy
//    @Query("SELECT image_url FROM MenuItem where menu = :id")
//    List<String> SearchAllMenu(@Param("id") long id);
    @Query("SELECT image_url FROM MenuItem where menu = 5")
    List<String> SearchAllMenu();

//    @Query(value = "SELECT image_url FROM menu_itmes WHERE menu_id = 5", nativeQuery = true)
//    List<String> SearchAllMenu();

}

