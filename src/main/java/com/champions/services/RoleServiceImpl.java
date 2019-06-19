package com.champions.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			
			System.out.println("No Role of ID " + id + " found");
		}
		
		return role;
	}

	@Override
	public Role findByRoleName(String name) {
		
		return roleDao.findByRoleName(name);
	}

}
