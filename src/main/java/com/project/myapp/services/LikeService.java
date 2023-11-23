package com.project.myapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.myapp.entities.Like;
import com.project.myapp.entities.Post;
import com.project.myapp.entities.User;
import com.project.myapp.repository.LikeRepository;
import com.project.myapp.requests.LikeCreateRequest;
import com.project.myapp.requests.LikeUpdateRequest;
import com.project.myapp.responses.LikeResponse;

@Service
public class LikeService {
	
	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;
	
	
	public LikeService(final LikeRepository likeRepository, final UserService userService,
			final PostService postService) {
		
		this.likeRepository=likeRepository;
		this.userService=userService;
		this.postService=postService;
	}


	public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			list =  likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = likeRepository.findByPostId(postId.get());
		}else 
			list =  likeRepository.findAll();
		return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
	}


	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}


	public Like createOneLike(LikeCreateRequest newLike) {
		User user = userService.getOneUserById(newLike.getUserId());
		Post post = postService.getOnePostById(newLike.getPostId());
		if(user !=null && post !=null) {
			Like likeToSave = new Like();
			likeToSave.setId(newLike.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else
		return null;
	}


	public Like updateOneLikeById(Long likeId, LikeUpdateRequest request) {
		Optional<Like> like = likeRepository.findById(likeId);
		if(like.isPresent()) {
			Like likeToUpdate = like.get();
			likeToUpdate.setPost(request.getPost());
			return likeRepository.save(likeToUpdate);
		}
		return null;
	}


	public void deleteLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}


	
	


	
	

}
