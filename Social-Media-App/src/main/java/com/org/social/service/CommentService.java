package com.org.social.service;

import org.springframework.stereotype.Service;

import com.org.social.model.Comment;


public interface CommentService {
	
	Comment createComment(Comment comment,int postId, int userId);
	
	Comment getById(int commentId);
	Comment likeComment(int commentId, int userId);
	
	

}
