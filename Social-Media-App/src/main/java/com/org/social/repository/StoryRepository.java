package com.org.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.social.model.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{
	
	List<Story> findByUserId(int userid);

}
