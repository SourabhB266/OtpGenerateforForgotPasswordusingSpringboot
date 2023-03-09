package com.ty.SpringBootForgotPassword_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.SpringBootForgotPassword_Project.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("select e from User e where e.email = ?1")
	public User getByEmail(String email);
}
