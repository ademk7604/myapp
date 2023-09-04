package com.project.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
