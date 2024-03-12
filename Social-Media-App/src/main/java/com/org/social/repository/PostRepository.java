package com.org.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.social.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUserId(int id);
}