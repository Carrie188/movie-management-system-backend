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
import com.example.demo.request.LoginRequest;
import com.example.demo.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			Optional<User> usertData = userRepository.findByEmail(loginRequest.getEmail());
					if (usertData.isPresent()) {
				String password = usertData.get().getPassword();
				if (password.equals(loginRequest.getPassword())) {
					return new ResponseEntity<>(usertData.get(), HttpStatus.OK);
				}
				MessageResponse msg = new MessageResponse("Incorrect password");
				return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
			}
			MessageResponse msg = new MessageResponse("No such a user");
			return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			MessageResponse msg = new MessageResponse("Server Error");
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

	
	
}
