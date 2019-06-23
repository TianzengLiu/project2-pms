package com.champions.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.champions.exceptions.UserNotFoundException;
import com.champions.models.NewUser;
import com.champions.models.Permit;
import com.champions.models.Role;
import com.champions.models.User;
import com.champions.repositories.UserDao;
import com.champions.models.Credentials;

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
	public User login(Credentials cred) {
		// TODO Auto-generated method stub
		return userDao.findByUsernameAndPassword(cred.getUsername(), cred.getPassword());
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
			throw new UserNotFoundException(err);
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
			throw new UserNotFoundException(err);
		}
		
		return user;
		
	}

	// Save method currently only used internally to save a valid User object	 
	@Override
	public User save(User user) {
		
		return userDao.save(user);
	}

	// Unused series of methods to construct a user from an arbitrary
	// JSON object, with manual validation of required fields.	 
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
	
	
	// The save method used by the user save endpoint to 
	// convert a NewUser into a valid User and store it 
	@Override
	public User save(NewUser newUser) {
		
		User user = newUserIntoUser(newUser);
		userDao.save(user);

		return user;
	}
	
	private User newUserIntoUser(NewUser newUser) {
		
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String first = newUser.getFirstName();
		String last = newUser.getLastName();
		String email = newUser.getEmail();
		String address = newUser.getAddress();
		
		Role role = roleService.findById(newUser.getRoleId());
		
		return new User(0, username, password, first, last, email, address, role, null);
	}
	
	@Override
	public void delete(Integer id) {
		
		userDao.deleteById(id);
	}
	
	// Extracts appropriate updates from a map and applies them to User of id
	@Override
	public User update(Map<String, Object> updates, int id) {
				
		User user = null;
		
		try {
			user = userDao.findById(id).get();
			
		} catch(NoSuchElementException e) {
			
			String err = "UserService update method failed to find user of ID " + id;
			throw new UserNotFoundException(err);
		}
		
		if(user != null) {
			
			final User finalUser = user;
			updates.forEach((s, o) -> {fillUser(finalUser, s, o);});
			save(user);
		}
		
		return user;
	}
	
	// Fills field denoted by String s on User s with appropriately cast Object o
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
