package com.example.demo.exception;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	 private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
	 
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleBadRequesEntity(MethodArgumentTypeMismatchException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "Bad arguments");
        body.put("status", 400);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> DataNotFound(DataNotFoundException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", ex.getMessage());
        body.put("status", 404);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(AlreadyPresentOnEventListException.class)
	public ResponseEntity<Object> handleAlreadyPresentOnEventListException(AlreadyPresentOnEventListException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "You are already signed to this event");
        body.put("status", 409);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);	
	}
	@ExceptionHandler(TheTeamCantPlayWithItselfException.class)
	public ResponseEntity<Object> handleTheTeamCantPlayWithItselfException(TheTeamCantPlayWithItselfException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "The same team cant play with itself");
        body.put("status", 409);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);	
	}
	@ExceptionHandler(NoAvailablePlacesException.class)
	public ResponseEntity<Object> handleNoAvailablePlacesException(NoAvailablePlacesException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "No available places");
        body.put("status", 409);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);	
	}
	@ExceptionHandler(EmailAlreadyBusyExceprion.class)
	public ResponseEntity<Object> handleEmailAlreadyBusyExceprionException(EmailAlreadyBusyExceprion ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "Email is aready busy");
        body.put("status", 409);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);	
	}
	@ExceptionHandler(WrongEmailOrPasswordException.class)
	public ResponseEntity<Object> handleWrongEmailOrPasswordException(WrongEmailOrPasswordException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", sdf.format(new Timestamp(System.currentTimeMillis())));
        body.put("message", "Wrong password or email");
        body.put("status", 404);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);	
	}
}
