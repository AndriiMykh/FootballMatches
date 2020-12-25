package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Event;
import com.example.demo.exception.TheTeamCantPlayWithItselfException;
import com.example.demo.service.EventService;


@RestController
@RequestMapping("/api/events")
@CrossOrigin("http://localhost:4200")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	private List<Event> getAllEvents(){
		return eventService.returnAllEvents();
	}
	
	@PostMapping("/")
	private ResponseEntity<Event> createNewEvent(@RequestBody Event event){
		if(event.getHost().equals(event.getGuest()))
			throw new TheTeamCantPlayWithItselfException(); 
		eventService.save(event);
		return ResponseEntity.ok(event);
	}
	
}
