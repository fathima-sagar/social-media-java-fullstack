package com.org.social.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThrowException extends RuntimeException{

	public ThrowException(String message) {
		super(message);
	}
}
