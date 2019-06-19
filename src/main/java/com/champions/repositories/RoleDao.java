package com.champions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.champions.models.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

	public Role findByRoleName(String name);
}
