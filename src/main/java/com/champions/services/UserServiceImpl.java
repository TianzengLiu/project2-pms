package com.champions.services;

import java.util.List;

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

}
