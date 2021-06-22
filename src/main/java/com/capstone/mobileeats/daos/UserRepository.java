package com.capstone.mobileeats.daos;

import com.capstone.mobileeats.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}