package com.org.social.service;

import java.util.List;

import com.org.social.model.Chat;
import com.org.social.model.User;

public interface ChatService {
	
	Chat createChat(User reqUser, User user);
	
	Chat findChatById(int chatId);
	
	List<Chat> findUsersChat(int userId);

}
