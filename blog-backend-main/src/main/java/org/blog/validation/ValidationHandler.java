package org.blog.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
//click source -> override/implement Methods -> select handleMethodArgumentNotValid 
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//getBindingResult() : Return the BindingResult that this BindException wraps.
        //getAllErrors() : Get all errors, both global and field ones. Returns a list of ObjectError instances
		
		Map<String,String> response=new HashMap<>();
		List<ObjectError> err= ex.getBindingResult().getAllErrors();
		
		//Lamda exp
		err.forEach(
				(error) ->{
					String fieldName=((FieldError)error).getField();//Encapsulates a field error, that is, a reason for rejecting a specificfield value. 
					String messsage=error.getDefaultMessage();
					
					response.put(fieldName, messsage);
				}
				);
		
		return new ResponseEntity<Object> (response,HttpStatus.BAD_REQUEST);
		
	}

	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		
//		Map<String, String> response=new HashMap<>();
//		
//		List<ObjectError> err= ex.getBindingResult().getAllErrors();
//		
//		
//		err.forEach(
//				(error) ->{
//					String fieldName=((FieldError)error).getField();
//					String message=error.getDefaultMessage();
//					
//					response.put(fieldName, message);
//				}
//				
//				);
//		
//		
//		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
//	}
	
	

	
	
}
//ResponseEntityExceptionHandler : A convenient base class for @ControllerAdvice classes that wish to provide 
//centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods. 

//MethodArgumentNotValidException : Exception to be thrown when validation on an argument annotated with @Valid fails.

//WebRequest (I) : use to give access to metadata like discriptions , headers etc.

//handleMethodArgumentNotValid : Customize the response for MethodArgumentNotValidException. 

//objectError : Encapsulates an object error, that is, a global reason for rejectingan object. 
