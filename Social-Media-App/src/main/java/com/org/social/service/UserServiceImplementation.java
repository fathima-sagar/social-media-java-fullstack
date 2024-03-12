package com.org.social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.social.config.JWTProvider;
import com.org.social.exceptions.ThrowException;
import com.org.social.model.User;
import com.org.social.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		Optional<User> userExist = userRepository.findByEmailId(user.getEmailId());
		
		if(userExist.isPresent()) {
			throw new ThrowException("Email already exist with mail provided");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		User savedUser=	userRepository.save(user);
		return savedUser;
	
	}

	@Override
	public User findUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ThrowException("User Not Found"));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmailId(email).orElseThrow(()->new ThrowException("Email Not Found"));
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) {
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user,int id) {
		User user2 = userRepository.findById(id).orElseThrow(()->new ThrowException("User Not Found"));
			if(user.getFirstName()!=null) {
				user2.setFirstName(user.getFirstName());
			}
			if(user.getLastName()!=null) {
				user2.setLastName(user.getLastName());
			}
			if(user.getEmailId()!=null) {
				user2.setEmailId(user.getEmailId());
			}
			if(user.getPassword()!=null) {
				user2.setPassword(user.getPassword());
			}
			if(user.getGender()!=null) {
				user2.setGender(user.getGender());
			}
			
			
			userRepository.save(user2);
		return user2;
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users=userRepository.searchUser(query);
		return users;
	}

	@Override
	public List<User> findAllUsers() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}

	@Override
	public User getUserFromToken(String jwt) {
		String email  = JWTProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

}
