package com.capstone.mobileeats.daos;

import com.capstone.mobileeats.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Vendor, Long>{

//    Vendor findByCategories(String query); //lists ads where title equals search

    //you can string together all kinds of methods, including filter and delete

    Vendor findByCategoriesLike(String searchPattern); //find by title LIKE "%searchPattern%"
}
