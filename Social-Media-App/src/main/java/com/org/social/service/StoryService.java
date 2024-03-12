package com.org.social.service;

import java.util.List;

import com.org.social.model.Story;
import com.org.social.model.User;

public interface StoryService {

	Story createStory(Story story, User user);
	
	List<Story> findUserStory(int userId);
}
