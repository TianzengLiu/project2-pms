package com.champions.services;

import java.util.List;
import java.util.Map;
import com.champions.models.NewUser;
import com.champions.models.User;
import com.champions.models.Credentials;

public interface UserService {

	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public User save(Map<String, Object> user);
	
	public User save(NewUser newUser);
	
	public User save(User user);
	
	public void delete(Integer id);
	
	public User update(Map<String, Object> updates, int id);
	
	public User findById(int userId);
	
	public User login(Credentials cred);
}
