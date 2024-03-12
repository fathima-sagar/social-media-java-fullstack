package com.org.social.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(Authorization -> Authorization
				.requestMatchers("/api/**")
				.authenticated()
				.anyRequest().permitAll())
		.addFilterBefore(new JWTValidator(), BasicAuthenticationFilter.class)
		.csrf(csrf-> csrf.disable())
		.cors(cors ->cors.configurationSource(corsConfigurationSource()));
		return http.build();
		
	}

	private CorsConfigurationSource corsConfigurationSource() {
		// TODO Auto-generated method stub
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				  CorsConfiguration configuration = new CorsConfiguration();
				    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
				    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
				    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
				    configuration.setExposedHeaders(Arrays.asList("Authorization"));
				    configuration.setAllowCredentials(true);
				    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
				    source.registerCorsConfiguration("/**", configuration);
				    return configuration;
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
