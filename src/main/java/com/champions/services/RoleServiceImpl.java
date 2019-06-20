package com.champions.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.champions.exceptions.RoleNotFoundException;
import com.champions.models.Role;
import com.champions.repositories.RoleDao;

@Service
public class RoleServiceImpl implements RoleService {

	public RoleDao roleDao;
	
	@Autowired
	public RoleServiceImpl(RoleDao rd) {
		
		this.roleDao = rd;
	}
	
	@Override
	public List<Role> findAll() {

		return roleDao.findAll();
	}

	@Override
	public Role findById(Integer id) {
		
		Role role = null;
		
		try {
			role = roleDao.findById(id).get();
		
		} catch(NoSuchElementException e) {
			
			String err = "RoleService failed to find role of ID " + id;
			throw new RoleNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return role;
	}

	@Override
	public Role findByRoleName(String name) {
		
		Role role = roleDao.findByRoleName(name);
		
		if(role == null) {
			
			String err = "RoleService failed to find role of name " + name;
			throw new RoleNotFoundException(HttpStatus.NOT_FOUND, err);
		}
		
		return role;
	}

}
