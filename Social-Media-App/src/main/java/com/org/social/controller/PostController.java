package com.org.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.model.Post;
import com.org.social.model.User;
import com.org.social.response.ApiResponse;
import com.org.social.service.PostService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/posts/save")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt){
		User loggedUser = userService.getUserFromToken(jwt);
		Post createPost = postService.createPost(post, loggedUser.getId());
		
		return new ResponseEntity<Post>(createPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/get/{postId}")
	public ResponseEntity<Post> getPost(@PathVariable int postId){
		Post getPost = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(getPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAll(){
		List<Post> getPost = postService.findAllPosts();
		
		return new ResponseEntity<>(getPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId,@RequestHeader("Authorization") String jwt){
		User loggedUser = userService.getUserFromToken(jwt);
		String getPost = postService.deletePost(postId, loggedUser.getId());
		
		return new ResponseEntity<>(new ApiResponse(getPost,true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/like")
	public ResponseEntity<Post> likedPosts(@PathVariable int postId,@RequestHeader("Authorization") String jwt){
		User loggedUser = userService.getUserFromToken(jwt);

		Post likedPost = postService.likePost(postId,loggedUser.getId() );
		
		return new ResponseEntity<>(likedPost,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/saved")
	public ResponseEntity<Post> savedPosts(@PathVariable int postId,@RequestHeader("Authorization") String jwt){
		User loggedUser = userService.getUserFromToken(jwt);
		Post savedPost = postService.savedPost(postId, loggedUser.getId());
		
		return new ResponseEntity<>(savedPost,HttpStatus.OK);
	}

}
