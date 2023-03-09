package com.ty.SpringBootForgotPassword_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.SpringBootForgotPassword_Project.dto.User;
import com.ty.SpringBootForgotPassword_Project.service.UserService;
import com.ty.SpringBootForgotPassword_Project.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/saveuser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PostMapping("/forgotpassword")
	public void genarateOtp(@RequestParam String email) {
		service.generateOtp(email);
	}
	
	@PostMapping("/verifyotp")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam int otp,@RequestParam String email){
		return service.verify(otp,email);
	}

}
