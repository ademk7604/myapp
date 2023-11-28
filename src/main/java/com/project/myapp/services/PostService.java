package com.project.myapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.myapp.entities.Post;
import com.project.myapp.entities.User;
import com.project.myapp.repository.PostRepository;
import com.project.myapp.requests.PostCreateRequest;
import com.project.myapp.requests.PostUpdateRequest;
import com.project.myapp.responses.LikeResponse;
import com.project.myapp.responses.PostResponse;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;

	public PostService(final PostRepository postRepository, final UserService userService) 
	{
		this.postRepository = postRepository;
		this.userService = userService;

	}
	// Circular Dependency hatasi Post ve Like serviceler birbirini cagiriyor
	// Alternative @Lazy final LikeService likeService oder in application.properties
	public void setLikeService(LikeService likeService) {
		this.likeService=likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> postList;
		if (userId.isPresent()) {
			postList = postRepository.findByUserId(userId.get());
		}else
			postList = postRepository.findAll();
		
		return postList.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),
					Optional.of(p.getId()));
			return new PostResponse(p, likes);
		}).collect(Collectors.toList());
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}
	
	public PostResponse getOnePostByIdWithLikes(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(postId));
		return new PostResponse(post, likes); 
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if (user == null)
			return null;
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		toSave.setCreateDate(LocalDateTime.now());
		return postRepository.save(toSave);

	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setTitle(updatePost.getTitle());
			toUpdate.setText(updatePost.getText());
			postRepository.save(toUpdate);
			return toUpdate;

		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}

}
