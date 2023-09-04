package com.project.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.myapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
