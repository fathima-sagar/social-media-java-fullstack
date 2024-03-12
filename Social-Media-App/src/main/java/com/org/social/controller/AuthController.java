package com.org.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.social.config.JWTProvider;
import com.org.social.model.User;
import com.org.social.response.AuthResponse;
import com.org.social.response.LoginRequest;
import com.org.social.service.CustomUserDetailsService;
import com.org.social.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public AuthResponse saveUser(@RequestBody User user) {
	User savedUser=service.registerUser(user);
	
	Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmailId(),savedUser.getPassword());
	
	String token = JWTProvider.generateToken(authentication);
	
		return new AuthResponse(token, "Successfully registered");
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		//username = email
		Authentication authentication = authenticate(loginRequest.getEmailId(),loginRequest.getPassword());

		String token = JWTProvider.generateToken(authentication);
		
			return new AuthResponse(token, "Login Successful");
		
	}


	private Authentication authenticate(String username, String password) {
		
		UserDetails userByUsername = customUserDetailsService.loadUserByUsername(username);
		if(userByUsername == null) {
			throw new BadCredentialsException("Invalid username");
		}
		System.out.println("decryptmypwd"+": "+password);	//1234
		System.out.println("decryptpwd"+": "+ userByUsername.getPassword());  //1234
		System.out.println("decryptmypwd"+": "+encoder.encode(password));
		System.out.println("decryptpwd"+": "+ encoder.encode(userByUsername.getPassword()));
		if(!encoder.matches(password, userByUsername.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		
		Authentication auth = new UsernamePasswordAuthenticationToken( userByUsername,null,userByUsername.getAuthorities());
		return auth;
	}
	

}
