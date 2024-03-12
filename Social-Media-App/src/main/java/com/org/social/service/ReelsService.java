package com.org.social.service;

import java.util.List;

import com.org.social.model.Reel;
import com.org.social.model.User;

public interface ReelsService {

	Reel createReel(Reel reel,User user);
	
	List<Reel> findAllReels();
	
	List<Reel> findUsersReel(int userId);
}
