package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champions.models.User;
import com.champions.repositories.UserDao;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao ud) {
		
		this.userDao = ud;
	}
	
	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public List<User> findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

	@Override
	public User save(User user) {
		
		return userDao.save(user);
	}
	
	@Override
	public User update(Map<String, Object> updates, int id) {
		
		/**
		User user = null;
		Optional<User> result = userDao.findById(id);
		
		try {
			
			user = result.get();
			
		} catch(NoSuchElementException e) {
			
			System.out.println("Update User failed to find user of id" + id);
		}
		*/
		updates.forEach((s, o) -> {System.out.println(s + " " + o);});
		
		return null;
	}

}
