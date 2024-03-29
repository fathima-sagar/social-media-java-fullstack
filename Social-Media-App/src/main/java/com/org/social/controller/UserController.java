package com.org.social.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.model.Post;
import com.org.social.model.User;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
	User savedUser=userService.registerUser(user);
		return savedUser;
	}
	
	@GetMapping("/get/{userId}")
	public User getUser(@PathVariable int userId) {
	User user=userService.findUserById(userId);
		return user;
	}
	
	@GetMapping("/user/{mail}")
	public User getByMail(@PathVariable String mail) {
	User user=userService.findUserByEmail(mail);
	return user;
	}
	
	@PutMapping("/update/user")
	public User updateUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) {
		User loggedUser = userService.getUserFromToken(jwt);
	User updateduser=userService.updateUser(user,loggedUser.getId());
	return updateduser;
	}
	
	@GetMapping("/users/search")
	public List<User> updateUser(@RequestParam("query") String param) {
	List<User> updateduser=userService.searchUser(param);
	return updateduser;
	}
	
	@PutMapping("users/follow/{userId2}")
	public User followUser(@RequestHeader("Authorization") String jwt,@PathVariable int userId2) {
		User loggedUser = userService.getUserFromToken(jwt);
		User followUser = userService.followUser(loggedUser.getId(), userId2);
		return followUser;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userService.findAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/user/profile")
	public User getUserByJwt(@RequestHeader("Authorization") String jwt) {
		User user = userService.getUserFromToken(jwt);
		return user;
	}
}
