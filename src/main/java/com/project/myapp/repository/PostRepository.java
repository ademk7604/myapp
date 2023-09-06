package com.project.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

	

	



}
