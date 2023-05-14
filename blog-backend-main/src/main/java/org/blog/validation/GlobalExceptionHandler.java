package org.blog.validation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.blog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionhandler(ResourceNotFoundException ex, WebRequest request)
	{
		Map<String, String> response=new HashMap<String, String>();
		
		response.put("date", new Date().toString());
		response.put("message", ex.getMessage());
		response.put("description", request.getDescription(false));//false coz wants only return URI else it return all description that we dont want
		
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exceptionhandler(Exception ex, WebRequest request)
	{
		Map<String, String> response=new HashMap<String, String>();
		
		response.put("date", new Date().toString());
		response.put("message", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
//WebRequest : Generic interface for a web request. Mainly intended for generic webrequest interceptors,
//giving them access to general request metadata,not for actual handling of the request.
