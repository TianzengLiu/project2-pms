package com.champions.services;

import java.util.List;

import com.champions.models.User;

public interface UserService {

	public List<User> findAll();
	
	public List<User> findByUsername(String username);
	
	public User save(User user);
}
