package com.project.myapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.myapp.entities.Comment;
import com.project.myapp.repository.CommentRepository;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	
	public CommentService(final CommentRepository commentRepository) {
		this.commentRepository=commentRepository;
	}

	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	
}
