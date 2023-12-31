package com.project.myapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.entities.Comment;
import com.project.myapp.requests.CommentCreateRequest;
import com.project.myapp.requests.CommentUpdateRequest;
import com.project.myapp.responses.CommentResponse;
import com.project.myapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;

	public CommentController(final CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping
	public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, 
												@RequestParam Optional<Long> postId) {

		return commentService.getAllCommentsWithParam(userId, postId);
	}

	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}

	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest newComment) {
		return commentService.createOneComment(newComment);
	}

	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
		return commentService.updateOneCommentById(commentId, request);
	}

	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Long commentId) {
		commentService.deleteCommentById(commentId);
	}

}
