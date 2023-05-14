package org.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends Exception{
	
	private String message;

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
}