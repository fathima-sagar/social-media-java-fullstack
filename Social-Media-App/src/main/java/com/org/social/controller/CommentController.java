package com.org.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.model.Comment;
import com.org.social.model.User;
import com.org.social.service.CommentService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add/comment/{postId}")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,@PathVariable int postId){
		
		User user = userService.getUserFromToken(jwt);
		Comment savedComment = commentService.createComment(comment, postId, user.getId());
		return new ResponseEntity<Comment>(savedComment,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/comment/{commentId}")
	public ResponseEntity<Comment> createComment(@PathVariable int commentId){
		
		Comment comment = commentService.getById(commentId);
		return new ResponseEntity<>(comment,HttpStatus.OK);
		
	}
	
	@PutMapping("/liked/comment/{commentId}")
	public ResponseEntity<Comment> createComment(@PathVariable int commentId, @RequestHeader("Authorization") String jwt){
		User user = userService.getUserFromToken(jwt);
		Comment comment = commentService.likeComment(commentId,user.getId());
		return new ResponseEntity<>(comment,HttpStatus.OK);
		
	}

}
