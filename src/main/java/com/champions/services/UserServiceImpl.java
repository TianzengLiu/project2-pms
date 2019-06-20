package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.champions.exceptions.UserNotFoundException;
import com.champions.models.Permit;
import com.champions.models.Role;
import com.champions.models.User;
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
	public User findByUsername(String username) {
		
		User user = userDao.findByUsername(username);
		
		if(user == null) {
			
			String err = "UserService failed to find user of username " + username;
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return user;
	}
	
	@Override
	public User findById(int userId) {
		
		User user = null;
		
		try {
			user = userDao.findById(userId).get();
			
		} catch(NoSuchElementException e) {
			
			String err = "UserService failed to find user of ID " + userId;
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return user;
		
	}

	@Override
	public User save(User user) {
		
		return userDao.save(user);
	}
	
	@Override
	public User save(Map<String, Object> newUser) {
		
		User user = null;
		
		if(validNewUser(newUser)) {
			
			user = unpackNewUser(newUser);
			userDao.save(user);
		}
		
		return user;
	}
	
	private boolean validNewUser(Map<String, Object> user) {
		
		Set<String> keys = user.keySet();
		
		boolean valid = keys.contains("username") && 
						keys.contains("password") &&
						keys.contains("email") &&
						keys.contains("firstName") && 
						keys.contains("lastName") &&
						keys.contains("address");
		
		return valid;
	}
	
	private User unpackNewUser(Map<String, Object> newUser) {
		
		String username = (String)newUser.get("username");
		String password = (String)newUser.get("password");
		String first = (String)newUser.get("firstName");
		String last = (String)newUser.get("lastName");
		String email = (String)newUser.get("email");
		String address = (String)newUser.get("address");
		
		Role role = roleService.findById((Integer)newUser.get("roleId"));
		
		return new User(0, username, password, first, last, email, address, role, null);
	}
	
	@Override
	public void delete(Integer id) {
		
		userDao.deleteById(id);
	}
	
	@Override
	public User update(Map<String, Object> updates, int id) {
		
		// test output
		//updates.forEach((s, o) -> {System.out.println(s + " " + o + " " + o.getClass());});
		
		User user = null;
		
		try {
			user = userDao.findById(id).get();
			
		} catch(NoSuchElementException e) {
			
			String err = "UserService update method failed to find user of ID " + id;
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, err);
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
