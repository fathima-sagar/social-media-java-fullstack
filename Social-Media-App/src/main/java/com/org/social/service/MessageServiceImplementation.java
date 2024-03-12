package com.org.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Chat;
import com.org.social.model.Message;
import com.org.social.model.User;
import com.org.social.repository.ChatRepository;
import com.org.social.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, int chatId, Message msg) {
		Chat chat = chatRepository.findById(chatId).orElseThrow(()->new ThrowException("Chat with Id not found"));
		Message message = new Message();
		message.setContent(msg.getContent());
		message.setImage(msg.getImage());
		message.setUser(user);
		message.setChat(chat);
		
		Message savedMessage = messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(int chatId) {
		
		 chatRepository.findById(chatId).orElseThrow(()->new ThrowException("Chat with Id not found"));

		return messageRepository.findByChatId(chatId);
	}

}
