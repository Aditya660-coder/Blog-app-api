package com.adityarai1.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adityarai1.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse= new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
	
	// This annotation specifies that this method handles MethodArgumentNotValidException exceptions.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	    // Create a map to store field names and their corresponding error messages.
	    Map<String, String> res = new HashMap<>();

	    // Retrieve the validation errors from the MethodArgumentNotValidException.
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        // Extract the name of the field where the validation error occurred.
	        String fieldName = ((FieldError) error).getField();
	        
	        // Extract the error message associated with the validation error.
	        String message = error.getDefaultMessage();
	        
	        // Put the field name and error message into the result map.
	        res.put(fieldName, message);
	    });

	    // Return a ResponseEntity containing the result map with a "Bad Request" status.
	    return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
	}

}
