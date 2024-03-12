package com.org.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.model.Reel;
import com.org.social.model.User;
import com.org.social.service.ReelsService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class ReelsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReelsService reelsService;
	
	@PostMapping("/create/reel")
	public ResponseEntity<Reel> createReel(@RequestBody Reel reel,@RequestHeader("Authorization") String jwt){
		User user = userService.getUserFromToken(jwt);
		Reel createReel = reelsService.createReel(reel, user);
		return new ResponseEntity<>(createReel,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/reels")
	public ResponseEntity<List<Reel>> getReel(){
		List<Reel> reels = reelsService.findAllReels();
		return new ResponseEntity<>(reels,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/reels/user")
	public ResponseEntity<List<Reel>> getReelByUser(@RequestHeader("Authorization") String jwt){
		User user = userService.getUserFromToken(jwt);
		List<Reel> reels = reelsService.findUsersReel(user.getId());
		return new ResponseEntity<>(reels,HttpStatus.OK);
		
	}

}
