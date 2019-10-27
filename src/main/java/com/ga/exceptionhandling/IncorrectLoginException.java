package com.ga.exceptionhandling;

public class IncorrectLoginException extends RuntimeException {
	public IncorrectLoginException(String message) {
		super(message);
	}

}
