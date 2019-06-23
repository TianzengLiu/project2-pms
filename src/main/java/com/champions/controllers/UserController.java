package com.champions.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.champions.annotations.Authen;
import com.champions.models.NewUser;
import com.champions.models.User;
import com.champions.services.UserService;
import com.champions.models.Credentials;

@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;
	
	public UserController() {
		super();
	}

	@Autowired
	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	@Authen(roles = {"manager"})
	@GetMapping
	public List<User> findAll() {
		
		return userService.findAll();
	}
	
	@Authen(roles = {"manager"})
	@GetMapping("username/{username}")
	public User findByUsername(@PathVariable String username) {
		
		return userService.findByUsername(username);
	}
	
	@Authen(roles = {"manager"})
	@GetMapping("userId/{userId}")
	public User findById(@PathVariable int userId) {
		
		return userService.findById(userId);
	}
	
	/**
	 * Receives information necessary to create a new User via the NewUser model
	 * @param newUser
	 * 			Contains all necessary and required information to construct a new User
	 * @return
	 * 			The User object that is created and saved to the database
	 */
	@PostMapping
	public User saveUser(@Valid @RequestBody NewUser newUser) {
		
		return userService.save(newUser);
	}
	
	@Authen(roles = {"manager"})
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		
		userService.delete(Integer.parseInt(id));
		return ResponseEntity.ok("User of id " + id + " deleted");
	}

	/**
	 * Partial update PATCH endpoint allows patching only the fields provided in an
	 * arbitrary JSON object and ignoring irrelevant keys
	 * @param updates
	 * 			JSON object containing fields and their values to apply to a User
	 * @param id
	 * 			The ID of the User to update
	 * @return
	 * 			The updated User object
	 */
	@Authen(roles = {"manager"})
	@RequestMapping(value = "{id}", 
					method = RequestMethod.PATCH, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public User partialUpdate(
			@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
	    
	    return userService.update(updates, Integer.parseInt(id));
	}

}
