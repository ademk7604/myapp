package com.project.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

}
