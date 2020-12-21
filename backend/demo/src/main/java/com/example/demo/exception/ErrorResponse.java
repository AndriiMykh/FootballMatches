package com.example.demo.exception;

import java.sql.Timestamp;

public class ErrorResponse {
	private Timestamp timestamp;
	private String message;
	private int status;
	
	public ErrorResponse() {
		super();
	}
	public ErrorResponse(Timestamp timestamp, String message, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
