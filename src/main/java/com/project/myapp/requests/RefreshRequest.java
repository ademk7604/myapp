package com.project.myapp.requests;

import lombok.Data;

@Data
public class RefreshRequest {
	
	Long userId;
	String refreshToken;
	

}
