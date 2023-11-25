package com.project.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
	
	

}
