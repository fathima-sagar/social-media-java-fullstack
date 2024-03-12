package com.org.social.service;

import java.util.List;

import com.org.social.model.Post;

public interface PostService {

	Post createPost(Post post,int userId);
	
	String deletePost(int postId,int userId);
	
	List<Post> findPostByUserId(int userId);
	
	Post findPostById(int postId);
	
	List<Post> findAllPosts();
	
	Post savedPost(int postId,int userId);
	
	Post likePost(int postId,int userId);
	
}
