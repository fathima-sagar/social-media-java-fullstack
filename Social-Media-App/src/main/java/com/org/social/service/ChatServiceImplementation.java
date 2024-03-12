package com.org.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Chat;
import com.org.social.model.User;
import com.org.social.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(User reqUser, User user) {
		Chat isExist = chatRepository.findChatByUsers(user,reqUser);
		if(isExist != null) {
			return isExist;
		}
		
		Chat chat  = new Chat();
		chat.getUsers().add(user);
		chat.getUsers().add(reqUser);
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(int chatId) {
		
		return chatRepository.findById(chatId).orElseThrow(()->new ThrowException("chat with id not found"));
	}

	@Override
	public List<Chat> findUsersChat(int userId) {
		// TODO Auto-generated method stub
		return chatRepository.findByUsersId(userId);
	}

}
