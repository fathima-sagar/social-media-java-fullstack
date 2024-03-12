package com.org.social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Post;
import com.org.social.model.User;
import com.org.social.repository.PostRepository;
import com.org.social.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository repository;

	@Override
	public Post createPost(Post post, int userId) {
	User user=repository.findById(userId).orElseThrow(()->new ThrowException("User Not Found"));
	post.setUser(user);
	return postRepository.save(post);
	}

	@Override
	public String deletePost(int postId, int userId) {
		User user=repository.findById(userId).orElseThrow(()->new ThrowException("user id not found"));
		Post post = postRepository.findById(postId).orElseThrow(()->new ThrowException("post not found"));
		if(post.getUser().getId() != user.getId()) {
			throw new ThrowException("You cannot delete others post");
		}
		postRepository.delete(post);
		return "deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(int userId) {
	List<Post> posts =	postRepository.findByUserId(userId);
		return posts;
	}

	@Override
	public Post findPostById(int postId) {
	Post findById = postRepository.findById(postId).orElseThrow(()-> new ThrowException("post not found with the following id"));
		return findById;
	}

	@Override
	public List<Post> findAllPosts() {
		List<Post> findAll = postRepository.findAll();
		return findAll;
	}

	@Override
	public Post savedPost(int postId, int userId) {
		Post post = findPostById(postId);
		User user =  repository.findById(userId).orElseThrow(()-> new ThrowException("User not found with Id"));
		if(user.getSaved().contains(post)) {
			user.getSaved().remove(post);
		}
		else {
			user.getSaved().add(post);
		}
		repository.save(user);
		return post;
	}

	@Override
	public Post likePost(int postId, int userId) {
		Post postById = findPostById(postId);
		User user = repository.findById(userId).orElseThrow(()-> new ThrowException("User not found with Id"));
		if(postById.getLiked().contains(user)) {
			postById.getLiked().remove(user);
		}else {
			postById.getLiked().add(user);
		}
		
		return postRepository.save(postById);
	}

}
