package com.project.myapp.responses;

import com.project.myapp.entities.User;

import lombok.Data;

@Data
public class UserResponse {

	Long id;
	int avatarId;
	String userName;
	
	public UserResponse(User entity) {
		this.id=entity.getId();
		this.userName=entity.getUserName();
		this.avatarId=entity.getAvatar();
	}
}
