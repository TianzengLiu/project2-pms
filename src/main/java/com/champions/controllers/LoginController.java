package com.champions.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.champions.models.Credentials;
import com.champions.models.User;
import com.champions.services.UserService;

@RestController
@RequestMapping("user")
public class LoginController {
	
	private UserService userService;
	
	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("login")
	public User login(@RequestBody Credentials cred, HttpServletRequest req) {
		User user = userService.login(cred);
		req.getSession().setAttribute("user", user);
		
		return user;
		
	}

}
