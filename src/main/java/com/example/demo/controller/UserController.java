package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {

		try {
			List<User> users = new ArrayList<User>();
			if (name == null) {
//					courseRepository.findAll().forEach(courses::add);
				List<User> results = userRepository.findAll();
				for (User u : results) {
					users.add(u);
				}

			} else {
				userRepository.findByName(name).forEach(users::add);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateMovie(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setName(user.getName());
			_user.setEmail(user.getEmail());
			_user.setPassword(user.getPassword());
			_user.setType(user.getType());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> updateMovie(@RequestBody User user) {
		
		try {
			User newUser = new User(user.getEmail(),user.getName(),user.getPassword());
			newUser.setType(user.getType());
			User _user = userRepository.save(newUser);
			return new ResponseEntity<>(_user, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {

		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
