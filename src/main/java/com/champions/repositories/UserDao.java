package com.champions.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.champions.models.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

	public List<User> findByEmail(String email);
	
//	public Optional<User> findByUserId(int userId);
	
	//TODO: constraint-based queries
}
