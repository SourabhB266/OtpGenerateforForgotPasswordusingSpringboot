package com.ty.SpringBootForgotPassword_Project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.SpringBootForgotPassword_Project.dto.User;
import com.ty.SpringBootForgotPassword_Project.repository.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);
	}
}
