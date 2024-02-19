package com.cms.service.exceptions;

/**
 * Exception thrown when a requested resource is not found on the server.
 */
public class ResourceNotFoundException extends RuntimeException{
	/**
     * Constructs a new ResourceNotFoundException with a default message.
     * The default message is "Resource not found on the server".
     */
	public ResourceNotFoundException() {
		super("Resource not found on the server");
	}
	 /**
     * Constructs a new ResourceNotFoundException with a specified message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
