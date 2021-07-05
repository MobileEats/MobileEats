package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
//    @Query("SELECT AVG(rating) FROM Review WHERE vendor.id = :vendor")
//    @Query("SELECT rating FROM Review WHERE vendor = :vendor")
//    List<Review> getAverage(@Param("vendor") long vendor);

}

