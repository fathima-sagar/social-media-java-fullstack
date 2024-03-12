package com.org.social.service;

import java.util.List;

import com.org.social.model.Message;
import com.org.social.model.User;

public interface MessageService {

	Message createMessage(User user,int chatId,Message msg);
	
	List<Message> findChatMessages(int chatId);
	
	
}
