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

import com.project.myapp.entities.Like;
import com.project.myapp.requests.LikeCreateRequest;
import com.project.myapp.requests.LikeUpdateRequest;
import com.project.myapp.responses.LikeResponse;
import com.project.myapp.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {

	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
		return likeService.getAllLikesWithParam(userId, postId);
	}

	@GetMapping("/{likeId}")
	public Like createOneLike(@PathVariable Long likeId) {
		return likeService.getOneLikeById(likeId);
	}

	@PostMapping
	public Like createOnePost(@RequestBody LikeCreateRequest newLike) {
		return likeService.createOneLike(newLike);
	}

	@PutMapping("/{likeId}")
	public Like updateOneLike(@PathVariable Long likeId, @RequestBody LikeUpdateRequest request) {
		return likeService.updateOneLikeById(likeId, request);
	}

	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Long likeId) {
		likeService.deleteLikeById(likeId);
	}
}
