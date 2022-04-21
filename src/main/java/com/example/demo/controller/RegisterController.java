package com.example.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.RegisterRequest;
import com.example.demo.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RegisterController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {

		try {
			Optional<User> usertData = userRepository.findByEmail(registerRequest.getEmail());
			if (usertData.isPresent()) {
				MessageResponse msg = new MessageResponse("the email is already exist");
				return new ResponseEntity<>(msg,  HttpStatus.FORBIDDEN);
			}

			if(registerRequest.getEmail().isEmpty()) {
				MessageResponse msg = new MessageResponse("the email can not be empty!");
				return new ResponseEntity<>(msg,  HttpStatus.FORBIDDEN);
			}
			
			
			
			User newUser =  new User(registerRequest.getEmail(), registerRequest.getName(), registerRequest.getPassword());
			if(!registerRequest.getType().isEmpty()) {
				newUser.setType(registerRequest.getType());
			}
			
			userRepository.save(newUser);
//			MessageResponse msg = new MessageResponse("create new user successfully!");
			return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
				
		} catch (Exception e) {
			MessageResponse msg = new MessageResponse("Server Error");
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
}
