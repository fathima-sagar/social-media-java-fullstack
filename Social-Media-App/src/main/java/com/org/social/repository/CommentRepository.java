package com.org.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.social.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
