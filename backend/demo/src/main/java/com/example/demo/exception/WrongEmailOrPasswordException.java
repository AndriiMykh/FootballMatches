package com.example.demo.exception;

public class WrongEmailOrPasswordException extends RuntimeException {

	public WrongEmailOrPasswordException() {
		super();
	}

	public WrongEmailOrPasswordException(String message) {
		super(message);
	}

}
