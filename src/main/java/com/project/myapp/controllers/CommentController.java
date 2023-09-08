package com.project.myapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.entities.Comment;
import com.project.myapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private CommentService commentService;
	
	public CommentController(final CommentService commentService) {
		this.commentService=commentService;
	}
	@GetMapping
	public List<Comment> getAllComments(){
		return commentService.getAllComments();
	}

}
