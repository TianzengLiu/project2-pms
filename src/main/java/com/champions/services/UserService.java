package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.champions.models.User;

public interface UserService {

	public List<User> findAll();
	
	public List<User> findByUsername(String username);
	
	public User save(User user);
	
	public User update(Map<String, Object> updates, int id);
	
	public User findById(int userId);
}
