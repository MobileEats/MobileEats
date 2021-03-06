package com.capstone.mobileeats.repositories;

import com.capstone.mobileeats.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    User findByPassword(String password);
}