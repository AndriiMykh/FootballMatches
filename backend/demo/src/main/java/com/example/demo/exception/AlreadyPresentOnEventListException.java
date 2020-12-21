package com.example.demo.exception;

public class AlreadyPresentOnEventListException extends RuntimeException {
	public AlreadyPresentOnEventListException() {
	}

	public AlreadyPresentOnEventListException(String message) {
		super(message);
	}
}
