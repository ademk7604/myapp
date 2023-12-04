package com.project.myapp.services;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.myapp.entities.RefreshToken;
import com.project.myapp.entities.User;
import com.project.myapp.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Value("${refresh.token.expires.in}")
	Long expireSeconds;
	
	private RefreshTokenRepository refreshTokenRepository;
	
	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository=refreshTokenRepository;
	}
	
	public String createRefreshToken (User user) {
		RefreshToken token = new RefreshToken();
		token.setUser(user);
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate((Date) Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
		
	}
	
	
	public boolean isRefreshExpired(RefreshToken refreshToken) {
		return false;
	}
	
}
