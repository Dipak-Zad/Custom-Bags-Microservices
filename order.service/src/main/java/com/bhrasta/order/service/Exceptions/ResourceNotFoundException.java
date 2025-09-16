package com.bhrasta.order.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found on server!!!");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
	
}
