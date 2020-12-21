package com.example.demo.exception;

public class TheTeamCantPlayWithItselfException extends RuntimeException {

	public TheTeamCantPlayWithItselfException() {
		super();
	}

	public TheTeamCantPlayWithItselfException(String message) {
		super(message);
	}
	
}
