package com.project.myapp.responses;

import com.project.myapp.entities.Comment;

import lombok.Data;

@Data
public class CommentResponse {

	Long id;
	Long userId;
	String userName;
	String text;
	
	public CommentResponse(Comment entity) {
		this.id=entity.getId();
		this.userId=entity.getUser().getId();
		this.userName=entity.getUser().getUserName();
		this.text=entity.getText();
		
	}
}
