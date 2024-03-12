package com.org.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Story;
import com.org.social.model.User;
import com.org.social.repository.StoryRepository;
import com.org.social.repository.UserRepository;

@Service
public class StoryServiceImplementation implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Story createStory(Story story, User user) {
		
		Story createStory = new Story();
		createStory.setCaption(story.getCaption());
		createStory.setImage(story.getImage());
		createStory.setUser(user);
		return storyRepository.save(createStory);
	}

	@Override
	public List<Story> findUserStory(int userId) {
		userRepository.findById(userId).orElseThrow(()-> new ThrowException("User not found with id"));
		List<Story> users = storyRepository.findByUserId(userId);
		return users;
	}

}
