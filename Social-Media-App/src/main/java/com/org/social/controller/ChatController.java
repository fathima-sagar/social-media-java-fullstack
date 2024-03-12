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

import com.org.social.model.Chat;
import com.org.social.model.User;
import com.org.social.request.ChatRequest;
import com.org.social.service.ChatService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create/chat")
	public ResponseEntity<Chat> createChat(@RequestBody ChatRequest request, @RequestHeader("Authorization") String jwt){
		User reqUser = userService.getUserFromToken(jwt);
		User user = userService.findUserById(request.getUserId());
		Chat chat = chatService.createChat(reqUser, user);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}
	
	@GetMapping("/get/chats")
	public ResponseEntity<List<Chat>> getUsersChat(@RequestHeader("Authorization") String jwt){
		User reqUser = userService.getUserFromToken(jwt);
		List<Chat> chats = chatService.findUsersChat(reqUser.getId());
		return new ResponseEntity<>(chats, HttpStatus.OK);

	}
	
	@GetMapping("/get/chat/{chatId}")
	public ResponseEntity<Chat> getUsersChat(@PathVariable int chatId){
		Chat chat = chatService.findChatById(chatId);
		return new ResponseEntity<>(chat, HttpStatus.OK);

	}
	
}
