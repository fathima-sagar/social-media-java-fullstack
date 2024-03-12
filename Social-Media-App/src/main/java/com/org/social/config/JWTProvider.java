package com.org.social.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import com.org.social.constants.AppConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTProvider {
	
	private static SecretKey key = Keys.hmacShaKeyFor(AppConstant.SECRET_KEY.getBytes());
	
	
	public static String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
				.setIssuer("Yasmeen")
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("emailId", auth.getName())
				.signWith(key)
				.compact();
		return jwt;
		
	}
	
	public static String getEmailFromJwtToken(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email = String.valueOf(claims.get("emailId"));
		return email;
		
	}
	

}
