package com.champions.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.champions.models.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public List<User> findByUsername(String username);

	public List<User> findByEmail(String email);
	
	//TODO: constraint-based queries
}
