package com.org.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.social.model.Reel;

public interface ReelsRepository extends JpaRepository<Reel, Integer>{
	
	List<Reel> findByUserId(int userId);

}
