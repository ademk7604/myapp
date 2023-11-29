package com.project.myapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.entities.User;
import com.project.myapp.responses.UserResponse;
import com.project.myapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers().stream().map(u -> new UserResponse(u)).toList();

	}

	@PostMapping
	public UserResponse createUser(@RequestBody User newUser) {
		return new UserResponse(userService.saveOneUser(newUser));
	}

	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		// custom exception
		return userService.getOneUserById(userId);
	}

	@PutMapping("/{userId}")
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateOneUser(userId, newUser);

	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.getDeleteOneUser(userId);

	}
	
	
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId) {
		return userService.getUserActivity(userId);
	}


}
