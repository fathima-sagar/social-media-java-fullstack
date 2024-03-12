package com.org.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.social.exceptions.ThrowException;
import com.org.social.model.Reel;
import com.org.social.model.User;
import com.org.social.repository.ReelsRepository;
import com.org.social.repository.UserRepository;

@Service
public class ReelServiceImplementation implements ReelsService{
	
	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Reel createReel(Reel reel, User user) {
		Reel reels = new Reel();
		reels.setTitle(reel.getTitle());
		reels.setUser(user);
		reels.setVideo(reel.getVideo());
		return reelsRepository.save(reels);
	}

	@Override
	public List<Reel> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reel> findUsersReel(int userId) {
		userRepository.findById(userId).orElseThrow(()-> new ThrowException("User not found with id"));
		List<Reel> reels = reelsRepository.findByUserId(userId);
		return reels;
	}

}
