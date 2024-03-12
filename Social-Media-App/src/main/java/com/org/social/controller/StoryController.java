package com.org.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.model.Story;
import com.org.social.model.User;
import com.org.social.service.StoryService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/add/story")
	public ResponseEntity<Story> createReel(@RequestBody Story story,@RequestHeader("Authorization") String jwt){
		User user = userService.getUserFromToken(jwt);
		Story createdStory = storyService.createStory(story, user); 
		return new ResponseEntity<>(createdStory,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/story/{userId}")
	public ResponseEntity<List<Story>> getReel(@RequestHeader("Authorization") String jwt,@PathVariable int userId){
		User user = userService.getUserFromToken(jwt);
		List<Story> stories = storyService.findUserStory(userId);
		return new ResponseEntity<>(stories,HttpStatus.OK);
		
	}

}
