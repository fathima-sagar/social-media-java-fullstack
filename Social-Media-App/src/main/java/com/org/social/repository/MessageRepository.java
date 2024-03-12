package com.org.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.social.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	List<Message> findByChatId(int chatId);

}
