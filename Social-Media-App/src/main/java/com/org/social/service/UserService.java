package com.org.social.service;

import java.util.List;

import com.org.social.model.User;

public interface UserService {

	public User registerUser(User user);
	public User findUserById(Integer userId);
	public User findUserByEmail(String email);
	public User followUser(Integer requestedId,Integer followerId);
	public User updateUser(User user, int id);
	public List<User> searchUser(String query);
	public List<User> findAllUsers();
	public User getUserFromToken(String jwt);
}

