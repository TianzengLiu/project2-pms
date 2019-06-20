package com.champions.services;

import java.util.List;
import java.util.Map;

import com.champions.models.User;

public interface UserService {

	public List<User> findAll();
	
	public List<User> findByUsername(String username);
	
	public User save(User user);
	
	public void delete(Integer id);
	
	public User update(Map<String, Object> updates, int id);
}
