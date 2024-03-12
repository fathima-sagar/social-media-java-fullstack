package com.org.social.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Getter;
import lombok.Setter;

@RestControllerAdvice
@Getter
@Setter
public class ApplicationException {
	
	@ExceptionHandler(ThrowException.class)
	ResponseEntity<Map<String, Object>> handleApplicationexception(ThrowException throwException){
		Map<String, Object> map  = new HashMap<>();
		map.put("message", throwException.getMessage());
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		
		
	}

}
