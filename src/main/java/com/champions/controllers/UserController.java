package com.champions.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.champions.models.User;
import com.champions.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;
	
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> findAll() {
		
		return userService.findAll();
	}
	
	@GetMapping("username/{username}")
	public List<User> findByUsername(@PathVariable String username) {
		
		return userService.findByUsername(username);
	}
	
	@PostMapping
	public User saveUser(@Valid @RequestBody User user) {
		
		return userService.save(user);
	}
	
	@RequestMapping(value = "{id}", 
					method = RequestMethod.PATCH, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public User partialUpdateGeneric(
			@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
	    
	    return userService.update(updates, Integer.parseInt(id));
	}


}
