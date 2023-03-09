package com.ty.SpringBootForgotPassword_Project.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ty.SpringBootForgotPassword_Project.dao.UserDao;
import com.ty.SpringBootForgotPassword_Project.dto.User;
import com.ty.SpringBootForgotPassword_Project.repository.UserRepo;
import com.ty.SpringBootForgotPassword_Project.util.ResponseStructure;

@Service
public class UserService {

	@Autowired
	UserDao dao;
	@Autowired
	UserRepo repo;
	@Autowired
	JavaMailSender mailSender;

	public void generateOtp(String email) {

		int otp = new Random().nextInt(900000) + 100000;

		User user = repo.getByEmail(email);
		if (user == null) {
			throw new RuntimeException("User Not Found");
		}

		String subject = "Password Reset OTP";
		String text = "Your OTP for resetting Password is :" + otp;
		user.setOtp(otp);
		dao.saveUser(user);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User daoUser = dao.saveUser(user);
		if (daoUser != null) {
			structure.setMessage("saved");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(daoUser);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<User>> verify(int otp, String email) {
		User user = repo.getByEmail(email);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (user.getOtp() == otp) {
			structure.setMessage("saved");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(user);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		return null;
	}
}
