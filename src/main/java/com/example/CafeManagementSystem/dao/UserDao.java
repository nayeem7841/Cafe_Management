package com.example.CafeManagementSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.CafeManagementSystem.pojo.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmailId(@Param("email") String email);

}
