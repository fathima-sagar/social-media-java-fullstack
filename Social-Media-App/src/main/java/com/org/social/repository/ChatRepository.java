package com.org.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.social.model.Chat;
import com.org.social.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{

	List<Chat> findByUsersId(int userId);

	@Query("select c from Chat c where :user Member of c.users and :reqUser Member of c.users")
	Chat findChatByUsers(@Param("user") User user,@Param("reqUser")User reqUser);
}
