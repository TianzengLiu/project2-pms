package com.champions.services;

import java.util.List;

import com.champions.models.Role;

public interface RoleService {

	public List<Role> findAll();
	
	public Role findById(Integer id);
	
	public Role findByRoleName(String name);
	
}
