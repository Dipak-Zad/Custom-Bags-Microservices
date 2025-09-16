package com.bhrasta.order.service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bhrasta.order.service.Payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundEntity(ResourceNotFoundException ex)
	{
		String msg = ex.getMessage();
		ApiResponse build = ApiResponse.builder().message(msg).success(false).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ApiResponse>(build, HttpStatus.NOT_FOUND);
	}

}
