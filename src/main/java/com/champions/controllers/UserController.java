package com.champions.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champions.annotations.Authen;
import com.champions.models.Credentials;
import com.champions.models.User;
import com.champions.services.UserService;

import springfox.documentation.service.CredentialsGrant;


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
	@Authen(roles = { "manager", "admin" })
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
	
	@PostMapping("login")
	public User login(@RequestBody Credentials cred, HttpServletRequest req) {
		User user = userService.login(cred);
		req.getSession().setAttribute("user", user);
		return user;
	}
	
}
