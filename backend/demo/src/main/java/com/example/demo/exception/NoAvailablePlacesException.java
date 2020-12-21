package com.example.demo.exception;

public class NoAvailablePlacesException extends RuntimeException{

	public NoAvailablePlacesException() {
		super();
	}

	public NoAvailablePlacesException(String message) {
		super(message);
	}
	
}
