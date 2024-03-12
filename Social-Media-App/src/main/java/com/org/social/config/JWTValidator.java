package com.org.social.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.org.social.constants.AppConstant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwt = request.getHeader(AppConstant.JWT_HEADER);
		if(jwt != null) {
			try {
				String emailId = JWTProvider.getEmailFromJwtToken(jwt);
				List<GrantedAuthority> authorities = new ArrayList<>();
				Authentication authentication = new UsernamePasswordAuthenticationToken(emailId, null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid token...");
			}
		}
		filterChain.doFilter(request, response);
	}
	

}
