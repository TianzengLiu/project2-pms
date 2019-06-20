package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champions.models.Permit;
import com.champions.models.Role;
import com.champions.models.User;
import com.champions.repositories.RoleDao;
import com.champions.repositories.UserDao;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private RoleService roleService;
	private PermitService permitService;
	
	@Autowired
	public UserServiceImpl(UserDao ud, RoleService rs, PermitService ps) {
		
		this.userDao = ud;
		this.roleService = rs;
		this.permitService = ps;
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
		
		// test output
		//updates.forEach((s, o) -> {System.out.println(s + " " + o + " " + o.getClass());});
		
		User user = null;
		
		try {
			user = userDao.findById(id).get();
			
		} catch(NoSuchElementException e) {
			
			System.out.println("UserService update method failed to find user of ID " + id);
		}
		
		if(user != null) {
			
			final User finalUser = user;
			updates.forEach((s, o) -> {fillUser(finalUser, s, o);});
			save(user);
		}
		
		return user;
	}
	
	private void fillUser(User usr, String s, Object o) {
		
		switch(s) {
		
			case "username":
				usr.setUsername((String)o);
				break;
				
			case "password":
				usr.setPassword((String)o);
				break;
				
			case "address":
				usr.setAddress((String)o);
				break;
				
			case "email":
				usr.setEmail((String)o);
				break;
				
			case "firstName":
				usr.setFirstName((String)o);
				break;
				
			case "lastName":
				usr.setLastName((String)o);
				break;
				
			case "roleId":
				Role role = roleService.findById((Integer)o);
				if(role != null) { usr.setRole(role); }
				break;
				
			case "permitId":
				Permit permit = permitService.findById((Integer)o);
				if(permit != null) { usr.setPermit(permit); }
				break;
				
			default:
				break;
		}
	}

}
