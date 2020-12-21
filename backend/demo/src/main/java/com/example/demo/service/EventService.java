package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Service
@Transactional
public class EventService {
	@Autowired
	private EventRepository repo;
	
	public Optional<Event> returnEventById(long id) {
		return repo.findById(id);
	}
	
	public List<Event> returnAllEvents(){
		return (List<Event>) repo.findAll();
	}
	public Event save(Event event) {
		return repo.save(event);
	}
}
