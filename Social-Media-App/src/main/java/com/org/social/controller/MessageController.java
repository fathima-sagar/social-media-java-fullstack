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

import com.org.social.model.Message;
import com.org.social.model.User;
import com.org.social.service.MessageService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create/message/chat/{chatId}")
	public ResponseEntity<Message> createMessage(@RequestBody Message message,@RequestHeader("Authorization") String jwt,@PathVariable int chatId){
		
		User user = userService.getUserFromToken(jwt);
		Message message2 = messageService.createMessage(user, chatId, message);
		
		return new ResponseEntity<Message>(message2,HttpStatus.OK);
	}
	
	@GetMapping("/get/message/chat/{chatId}")
	public ResponseEntity<List<Message>> getMessages(@PathVariable int chatId){
		List<Message> message2 = messageService.findChatMessages(chatId);
		
		return new ResponseEntity<>(message2,HttpStatus.OK);
	}

}
