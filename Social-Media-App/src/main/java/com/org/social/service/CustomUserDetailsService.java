package com.org.social.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.org.social.model.User;
import com.org.social.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailId(username).orElseThrow(()-> new UsernameNotFoundException("User not found with mail"+username));
		
		List<GrantedAuthority> authorities = new ArrayList<>();
 		return new org.springframework.security.core.userdetails.User(user.getEmailId(),user.getPassword(),authorities);
	}
	
	
}
