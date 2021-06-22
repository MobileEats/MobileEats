package com.capstone.mobileeats.daos;

import com.capstone.mobileeats.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}

