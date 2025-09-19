package com.bhrasta.bag.service.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Bag not found on server!!!");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}

}
